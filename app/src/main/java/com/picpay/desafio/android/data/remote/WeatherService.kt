package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.remote.dto.CityWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    @GET("weather")
    suspend fun getCityWeather(
        @Query("q") cityName: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): Response<CityWeatherDto>

    @GET("weather")
    suspend fun getCoordinatorsWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") cnt: Int,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): Response<CityWeatherDto>
}