package com.ucb.usecases.Usuario

import com.ucb.data.repository.IAuthRepository
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Usuario

class RegistrarUsuario(
    private val repo: IAuthRepository)
{
    suspend operator fun invoke(email: String, password: String): Boolean {
        return repo.register(email, password)
    }
}