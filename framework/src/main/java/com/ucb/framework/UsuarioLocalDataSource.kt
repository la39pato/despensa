package com.ucb.framework

import android.content.Context
import com.ucb.data.local.IUsuarioLocalDataSource
import com.ucb.framework.Usuario.toEntity
import com.ucb.framework.Usuario.UserDao
import com.ucb.domain.Usuario

class UsuarioLocalDataSource(context: Context) : IUsuarioLocalDataSource {

    private val userDao: UserDao = AppRoomDatabase.getDatabase(context).userDao()

    override suspend fun registrar(correo: String, password: String): Boolean {
        return try {
            // Aquí deberías definir cómo registrar. Como UserDB tiene más campos,
            // puedes usar valores por defecto para nombre y productos.
            val nuevoUsuario = Usuario(
                nombre = "",  // o algún valor por defecto o parámetro adicional
                correo = correo,
                password = password,
                productos = ""
            )
            userDao.insert(nuevoUsuario.toEntity())
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun login(correo: String, password: String): Boolean {
        return try {
            val usuarioDB = userDao.login(correo, password)
            usuarioDB != null
        } catch (e: Exception) {
            false
        }
    }
}
