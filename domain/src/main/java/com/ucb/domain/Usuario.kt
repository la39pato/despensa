package com.ucb.domain

data class Usuario(
    val id: Int = 0,
    val nombre: String,
    val correo: String,
    val password: String,
    val productos: String
)