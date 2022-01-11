package com.picpay.desafio.android.domain.repositories

import com.picpay.desafio.android.domain.model.CityWeather
import com.picpay.desafio.android.domain.utils.Result

interface CityWeatherRepository {
    suspend fun getCityWeather(cityName: String, units: String): Result<CityWeather>

    suspend fun getCoordinatorsWeather(latitude: Double, longitude: Double, cnt: Int, units: String): Result<CityWeather>

}