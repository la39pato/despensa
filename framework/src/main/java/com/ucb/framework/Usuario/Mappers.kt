package com.ucb.framework.Usuario

import com.ucb.domain.Usuario

// De dominio a entidad de base de datos
fun Usuario.toEntity(): UserDB {
    return UserDB(
        nombre = nombre,
        correo = correo,
        password = password,
        productos = productos
    ).also { it.id = id }
}

// De entidad de base de datos a modelo de dominio
fun UserDB.toModel(): Usuario {
    return Usuario(
        id = id,
        nombre = nombre,
        correo = correo,
        password = password,
        productos = productos
    )
}

// Lista de entidades a lista de modelos
fun List<UserDB>.toModelList(): List<Usuario> {
    return this.map { it.toModel() }
}
