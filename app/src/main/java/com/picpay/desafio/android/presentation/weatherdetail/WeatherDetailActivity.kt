package com.picpay.desafio.android.presentation.weatherdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityWeatherDetailBinding
import com.picpay.desafio.android.domain.model.CityWeather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_weather_detail.*

const val WEATHER_DETAILS = "weather_details"

class WeatherDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherDetailBinding

    private lateinit var weatherDetails: CityWeather

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherDetails = intent.extras?.get(WEATHER_DETAILS) as CityWeather
        ivBack.setOnClickListener { onBackPressed() }

        fillScreen(weatherDetails)
    }

    private fun fillScreen(weatherDetails: CityWeather) {
        binding.tvCityName.text = weatherDetails.name

        binding.tvDegrees.text =
            getString(R.string.weather_detail_activity_celsius, weatherDetails.temp.toString())

        binding.tvWeather.text = getString(
            R.string.weather_detail_activity_weather,
            weatherDetails.weatherTitle,
            weatherDetails.weatherDescription
        )

        binding.tvMaxTempValue.text =
            getString(R.string.weather_detail_activity_celsius, weatherDetails.tempMax.toString())

        binding.tvMinTempValue.text =
            getString(R.string.weather_detail_activity_celsius, weatherDetails.tempMin.toString())

        binding.tvHumidityValue.text =
            getString(R.string.weather_detail_activity_percent, weatherDetails.humidity.toString())

        binding.tvWindSpeedValue.text = weatherDetails.windSpeed.toString()

        binding.tvPressureValue.text = weatherDetails.pressure.toString()

        binding.tvFeelsLikeValue.text =
            getString(R.string.weather_detail_activity_celsius, weatherDetails.feelsLike.toString())

        val icon = weatherDetails.weatherIcon
        val iconUrl = "http://openweathermap.org/img/w/$icon.png"
        Picasso.get()
            .load(iconUrl)
            .into(binding.ivWeather)
    }
}