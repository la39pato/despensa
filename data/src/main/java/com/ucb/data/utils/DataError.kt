package com.ucb.data.utils

sealed class DataError {
    object Database : DataError()
    object EmptyResult : DataError()
    data class Unknown(val message: String) : DataError()
}