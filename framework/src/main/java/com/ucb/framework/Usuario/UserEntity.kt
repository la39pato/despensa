package com.ucb.framework.Usuario

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "usuarios")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val correo: String,
    val password: String,
    val productos: String
)
