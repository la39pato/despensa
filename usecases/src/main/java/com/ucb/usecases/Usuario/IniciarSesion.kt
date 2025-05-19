package com.ucb.usecases.Usuario

import com.ucb.data.repository.AuthRepository
import com.ucb.data.utils.NetworkResult

class IniciarSesion(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(usuario: String, password: String): Boolean {
        return when (val result = repo.login(usuario, password)) {
            is NetworkResult.Success -> result.data
            is NetworkResult.Error -> false
        }
    }
}