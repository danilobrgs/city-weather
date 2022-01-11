package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.remote.RetrofitClient
import com.picpay.desafio.android.data.remote.WeatherService
import com.picpay.desafio.android.data.repositories.WeatherRepositoryImpl
import com.picpay.desafio.android.di.Properties.APPID
import com.picpay.desafio.android.di.Properties.BASE_URL
import com.picpay.desafio.android.domain.repositories.CityWeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single<WeatherService> {
        RetrofitClient.create(
            getProperty(BASE_URL)
        )
    }
    factory<CityWeatherRepository> {
        WeatherRepositoryImpl(get(), getProperty(APPID))
    }
}