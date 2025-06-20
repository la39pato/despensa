package com.ucb.usecases.Usuario

import com.ucb.data.repository.IAuthRepository


class IniciarSesion constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return authRepository.login(email, password)
    }
}
