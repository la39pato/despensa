package com.ucb.usecases.Usuario

import com.ucb.data.repository.AuthRepository

class IniciarSesion(private val repo: AuthRepository) {
    suspend operator fun invoke(usuario: String, contraseña: String): Boolean {
        return repo.login(usuario, contraseña)
    }
}