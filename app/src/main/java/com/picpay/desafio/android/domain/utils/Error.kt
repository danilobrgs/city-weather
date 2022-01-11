package com.picpay.desafio.android.domain.utils

sealed class Error {
    object NetworkError : Error()
    object ResponseError : Error()
}