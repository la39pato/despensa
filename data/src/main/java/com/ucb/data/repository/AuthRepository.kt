package com.ucb.data.repository

import com.ucb.data.local.IUsuarioLocalDataSource
import com.ucb.data.utils.NetworkResult

class AuthRepository (
    private val localDataSource: IUsuarioLocalDataSource
) {

    suspend fun login(correo: String, password: String): NetworkResult<Boolean> {
        return try {
            val success = localDataSource.login(correo, password)
            if (success) NetworkResult.Success(true)
            else NetworkResult.Error("Correo o contraseña incorrectos.")
        } catch (e: Exception) {
            NetworkResult.Error("Error al iniciar sesión: ${e.message}")
        }
    }

    suspend fun registrar(correo: String, password: String): NetworkResult<Boolean> {
        return try {
            val success = localDataSource.registrar(correo, password)
            if (success) NetworkResult.Success(true)
            else NetworkResult.Error("No se pudo registrar el usuario.")
        } catch (e: Exception) {
            NetworkResult.Error("Error al registrar: ${e.message}")
        }
    }
}