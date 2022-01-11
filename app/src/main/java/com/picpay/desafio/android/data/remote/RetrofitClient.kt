package com.picpay.desafio.android.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    inline fun <reified T> create(
        baseUrl: String
    ): T {
        val clientBuilder =
            OkHttpClient()
                .newBuilder()

        return Retrofit.Builder()
            .client(clientBuilder.build())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(T::class.java)
    }
}