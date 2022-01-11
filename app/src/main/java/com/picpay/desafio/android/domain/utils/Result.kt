package com.picpay.desafio.android.domain.utils

sealed class Result<T>(private val value: T?, private val failure: Error?) {
    data class Success<T>(val data: T) : Result<T>(data, null)
    data class Failure<T>(val errorInfo: Error) : Result<T>(null, errorInfo)

    fun <E> transform(
        onSuccess: (data: T) -> E
    ): Result<E> {
        return if (this is Success) {
            Success(onSuccess(data))
        } else {
            val error = this as Failure
            Failure(error.errorInfo)
        }
    }
}