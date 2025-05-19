package com.ucb.usecases.Usuario

import com.ucb.data.repository.AuthRepository
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Usuario

class RegistrarUsuario(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(usuario: Usuario): NetworkResult<Boolean> {
        return repo.registrar(usuario.correo, usuario.password)
    }
}