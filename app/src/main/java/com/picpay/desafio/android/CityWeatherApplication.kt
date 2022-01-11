package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.dataModule
import com.picpay.desafio.android.di.domainModule
import com.picpay.desafio.android.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class CityWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CityWeatherApplication)
            androidFileProperties()
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    presentationModule
                )
            )
        }
    }
}