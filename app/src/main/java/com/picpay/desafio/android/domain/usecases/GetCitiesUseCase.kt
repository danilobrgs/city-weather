package com.picpay.desafio.android.domain.usecases

import com.picpay.desafio.android.domain.model.CityWeather
import com.picpay.desafio.android.domain.repositories.CityWeatherRepository
import com.picpay.desafio.android.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCitiesUseCase(
    private val cityWeatherRepository: CityWeatherRepository
) {
    suspend fun getCityWeather(cityName: String, units: String): Result<CityWeather> = withContext(Dispatchers.IO) {
        cityWeatherRepository.getCityWeather(cityName, units)
    }

    suspend fun getCoordinatorsWeather(latitude: Double, longitude: Double, cnt: Int, units: String): Result<CityWeather> = withContext(Dispatchers.IO) {
        cityWeatherRepository.getCoordinatorsWeather(latitude, longitude, cnt, units)
    }
}