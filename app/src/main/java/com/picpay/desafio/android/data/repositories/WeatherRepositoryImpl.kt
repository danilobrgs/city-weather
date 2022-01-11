package com.picpay.desafio.android.data.repositories

import com.picpay.desafio.android.data.remote.WeatherService
import com.picpay.desafio.android.domain.model.CityWeather
import com.picpay.desafio.android.domain.repositories.CityWeatherRepository
import com.picpay.desafio.android.domain.utils.Error
import com.picpay.desafio.android.domain.utils.Result

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val appid: String
) : CityWeatherRepository {
    override suspend fun getCityWeather(cityName: String, units: String): Result<CityWeather> {
        return try {
            val response = weatherService.getCityWeather(
                cityName,
                appid,
                units
            )
            if (response.isSuccessful && response.body() != null) {
                return Result.Success(response.body()!!.toCityWeather())
            } else {
                Result.Failure(Error.ResponseError)
            }
        } catch (error: Exception) {
            Result.Failure(Error.NetworkError)
        }
    }

    override suspend fun getCoordinatorsWeather(
        latitude: Double,
        longitude: Double,
        cnt: Int,
        units: String
    ): Result<CityWeather> {
        return try {
            val response = weatherService.getCoordinatorsWeather(
                latitude,
                longitude,
                cnt,
                appid,
                units
            )

            if (response.isSuccessful && response.body() != null) {
                return Result.Success(response.body()!!.toCityWeather())
            } else {
                Result.Failure(Error.ResponseError)
            }
        } catch (error: Exception) {
            Result.Failure(Error.NetworkError)
        }
    }
}