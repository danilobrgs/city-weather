package com.picpay.desafio.android.presentation.cities.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.CityWeather
import com.picpay.desafio.android.presentation.utils.ResourcesHelper
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_city.view.*

class CitiesListItemViewHolder(
    itemView: View,
    context: Context,
    onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    var resourcesHelper: ResourcesHelper

    init {
        itemView.cvCityContainer.setOnClickListener {
            onItemClick(adapterPosition)
        }
        resourcesHelper = ResourcesHelper(context)
    }

    fun bind(cityWeather: CityWeather) {
        itemView.tvCityName.text = cityWeather.name
        itemView.country.text = resourcesHelper.getString(R.string.cities_activity_country, cityWeather.country)
        itemView.tvDegrees.text =
            resourcesHelper.getString(R.string.cities_activity_celsius, cityWeather.temp.toString())
        itemView.tvWeather.text =
            resourcesHelper.getString(
                R.string.cities_activity_weather,
                cityWeather.weatherTitle,
                cityWeather.weatherDescription
            )
        val icon = cityWeather.weatherIcon
        val iconUrl = "http://openweathermap.org/img/w/$icon.png"
        Picasso.get()
            .load(iconUrl)
            .into(itemView.ivWeather, object : Callback {
                override fun onSuccess() {
                    itemView.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    itemView.progressBar.visibility = View.GONE
                }
            })
    }
}