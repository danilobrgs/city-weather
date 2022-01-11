package com.picpay.desafio.android.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.domain.model.CityWeather

data class CityWeatherDto(
    val sys: Sys? = null,
    val main: Main? = null,
    val id: Int? = null,
    val name: String? = null,
    val wind: Wind? = null,
    val weather: List<Weather>? = null
) {
    fun toCityWeather(): CityWeather = CityWeather(
        id = id,
        name = name,
        weatherTitle = weather?.get(0)?.main,
        weatherDescription = weather?.get(0)?.description,
        weatherIcon = weather?.get(0)?.icon,
        temp = main?.temp?.toInt(),
        feelsLike = main?.feelsLike?.toInt(),
        humidity = main?.humidity,
        pressure = main?.pressure,
        tempMin = main?.tempMin?.toInt(),
        tempMax = main?.tempMax?.toInt(),
        country = sys?.country,
        windSpeed = wind?.speed
    )
}

data class Weather(
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null
)

data class Main(
    val temp: Float = 0.0f,
    @SerializedName("feels_like")
    val feelsLike: Float = 0.0f,
    val humidity: Float = 0.0f,
    val pressure: Float = 0.0f,
    @SerializedName("temp_min")
    val tempMin: Float = 0.0f,
    @SerializedName("temp_max")
    val tempMax: Float = 0.0f
)

data class Sys(
    val country: String? = null,
    val id: Int? = null
)

data class Wind(
    val speed: Float = 0.0f
)

