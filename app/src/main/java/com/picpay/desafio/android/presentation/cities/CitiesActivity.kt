package com.picpay.desafio.android.presentation.cities

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityCitiesBinding
import com.picpay.desafio.android.presentation.cities.adapter.CitiesListAdapter
import com.picpay.desafio.android.presentation.weatherdetail.WEATHER_DETAILS
import com.picpay.desafio.android.presentation.weatherdetail.WeatherDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCitiesBinding

    private val contactsViewModel by viewModel<CitiesViewModel>()

    private lateinit var citiesAdapter: CitiesListAdapter

    private lateinit var progressBar: ProgressBar

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val REQUEST_CODE_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = binding.citiesListProgressBar

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)

        grantLocationPermission()

        subscribeViewModelStates()

        initAdapter()
    }

    private fun initAdapter() {
        citiesAdapter = CitiesListAdapter {
            val intent = Intent(this, WeatherDetailActivity::class.java)
            intent.putExtra(WEATHER_DETAILS, it)
            startActivity(intent)
        }
        binding.recyclerView.adapter = citiesAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun subscribeViewModelStates() {
        contactsViewModel.presentationState.observe(this, { presentation ->
            when (presentation) {
                is CitiesState.Presentation.Success -> {
                    citiesAdapter.submitList(presentation.contactListPresentation.toMutableList())
                }
                is CitiesState.Presentation.NetworkError -> {
                    Toast.makeText(
                        this@CitiesActivity,
                        getString(R.string.network_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        contactsViewModel.actionState.observe(this, { action ->
            when (action) {
                is CitiesState.Action.Loading -> {
                    progressBar.visibility = if (action.showLoading) View.VISIBLE else View.GONE
                }
            }
        })
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    contactsViewModel.getCoordinatorsWeather(it.latitude, it.longitude)
                }
            }
    }

    private fun grantLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION_PERMISSION

            )
        } else {
            getCurrentLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
