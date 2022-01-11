package com.picpay.desafio.android.di

import com.picpay.desafio.android.presentation.cities.adapter.CitiesListAdapter
import com.picpay.desafio.android.presentation.cities.CitiesViewModel
import com.picpay.desafio.android.presentation.utils.ResourcesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        CitiesViewModel(
            getCitiesUseCase = get(),
            resourcesHelper = get()
        )
    }
    factory {
        ResourcesHelper(applicationContext = androidContext())
    }
}