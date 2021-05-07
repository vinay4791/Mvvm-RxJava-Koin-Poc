package com.example.rxjavakoinpoc.common

import com.example.rxjavakoinpoc.api.ErrorType

const val  NON_HTTP_EXCEPTION = -1

sealed class ResponseState<out T> {

    object Loading : ResponseState<Nothing>()

    data class Success<T>(val response: T) : ResponseState<T>()

    data class Error(val errorType: ErrorType) : ResponseState<Nothing>()
}
