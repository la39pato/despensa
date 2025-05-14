package com.ucb.data.utils

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Error(val error: DataError): NetworkResult<Nothing>()
}