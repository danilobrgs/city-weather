package com.picpay.desafio.android.di

import com.picpay.desafio.android.domain.usecases.GetCitiesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetCitiesUseCase(cityWeatherRepository = get())
    }
}