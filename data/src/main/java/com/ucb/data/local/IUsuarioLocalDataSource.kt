package com.ucb.data.local

interface IUsuarioLocalDataSource {
    suspend fun registrar(correo: String, password: String): Boolean
    suspend fun login(correo: String, password: String): Boolean
}