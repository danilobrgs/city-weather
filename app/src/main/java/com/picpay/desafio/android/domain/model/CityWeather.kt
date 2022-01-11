package com.picpay.desafio.android.domain.model

import java.io.Serializable

data class CityWeather(
    val id: Int? = 0,
    val name: String? = "",
    val weatherTitle: String? = "",
    val weatherDescription: String? = "",
    val weatherIcon: String? = "",
    val temp: Int? = 0,
    val feelsLike: Int? = 0,
    val humidity: Float? = 0.0f,
    val pressure: Float? = 0.0f,
    val tempMin: Int? = 0,
    val tempMax: Int? = 0,
    val country: String? = "",
    val windSpeed: Float? = 0.0f
) : Serializable
