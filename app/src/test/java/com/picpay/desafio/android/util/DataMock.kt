package com.picpay.desafio.android.util

import com.picpay.desafio.android.data.remote.dto.*
import com.picpay.desafio.android.domain.model.CityWeather
import com.picpay.desafio.android.domain.utils.Result
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

val cityWeatherDtoMock = CityWeatherDto(
    sys = Sys(
        country = "BR",
        id = 123
    ),
    main = Main(
        temp = 20.0f,
        feelsLike = 22.0f,
        humidity = 60.0f,
        pressure = 110.0f,
        tempMin = 10.0f,
        tempMax = 30.0f
    ),
    id = 12,
    name = "Monte City",
    wind = Wind(
        speed = 10.0f
    ),
    weather = listOf(
        Weather(
            main = "Nuvens",
            description = "Bastante nuvens",
            icon = "icon"
        )
    )
)

val cityWeatherMock = CityWeather(
    id = 12,
    name = "Monte City",
    weatherTitle = "Nuvens",
    weatherDescription = "Bastante nuvens",
    weatherIcon = "icon",
    temp = 20,
    feelsLike = 22,
    humidity = 60.0f,
    pressure = 110.0f,
    tempMin = 10,
    tempMax = 30,
    country = "BR",
    windSpeed = 10.0f
)

val responseCityWeatherDtoMock = Response.success(cityWeatherDtoMock)

val errorResponse =
    "{\n" +
            "  \"type\": \"error\",\n" +
            "  \"message\": \"What you were looking for isn't here.\"\n" +
            "}"

val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
val errorMockResponse = Response.error<CityWeatherDto>(404, errorResponseBody)