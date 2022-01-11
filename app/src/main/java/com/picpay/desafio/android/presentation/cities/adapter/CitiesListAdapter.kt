package com.picpay.desafio.android.presentation.cities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.CityWeather

class CitiesListAdapter(private val onItemClick: (CityWeather) -> Unit) :
    ListAdapter<CityWeather, CitiesListItemViewHolder>(CitiesListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_city, parent, false)

        return CitiesListItemViewHolder(view, parent.context) {
            getItem(it)?.let { item -> onItemClick(item) }
        }
    }

    override fun onBindViewHolder(holder: CitiesListItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}