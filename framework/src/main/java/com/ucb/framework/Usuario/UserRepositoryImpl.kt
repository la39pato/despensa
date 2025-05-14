package com.ucb.framework.Usuario

import com.ucb.data.repository.AuthRepository
import com.ucb.data.utils.DataError
import com.ucb.data.utils.NetworkResult

class UserRepositoryImpl(private val dao: UserDao) : AuthRepository {
    override suspend fun login(correo: String, password: String): NetworkResult<Boolean> {
        val user = dao.login(correo, password)
        return if (user != null) {
            NetworkResult.Success(true)
        } else {
            NetworkResult.Error(DataError.Unknown("Usuario o contraseña incorrectos"))
        }
    }
}