package com.picpay.desafio.android.presentation.utils

import android.content.Context
import androidx.annotation.StringRes

class ResourcesHelper(private val applicationContext: Context) {
    fun getStringArray(resId: Int): Array<String> =
        applicationContext.resources.getStringArray(resId)

    fun getString(resId: Int, value: String? = null, valueTwo: String? = null): String =
        if (!valueTwo.isNullOrBlank()) {
            applicationContext.resources.getString(resId, value, valueTwo)
        } else {
            value?.let { applicationContext.resources.getString(resId, value) }
                ?: applicationContext.resources.getString(resId)
        }
}