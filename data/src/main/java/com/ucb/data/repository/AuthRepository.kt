package com.ucb.data.repository

import com.ucb.data.utils.NetworkResult

interface AuthRepository {
    suspend fun login(correo: String, password: String): NetworkResult<Boolean>
}