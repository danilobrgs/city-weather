package com.picpay.desafio.android.presentation.cities.adapter

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.domain.model.CityWeather

class CitiesListDiffCallback : DiffUtil.ItemCallback<CityWeather>() {
    override fun areItemsTheSame(oldItem: CityWeather, newItem: CityWeather): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CityWeather, newItem: CityWeather): Boolean =
        oldItem == newItem
}