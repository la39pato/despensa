package com.ucb.framework.Usuario

import com.ucb.domain.Usuario


fun UserEntity.toDomain(): Usuario = Usuario(id, nombre, correo, password , productos)

fun Usuario.toEntity(): UserEntity = UserEntity(id = id, nombre = nombre, correo = correo, password = password , productos = productos)