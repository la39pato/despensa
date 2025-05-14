package com.ucb.domain

data class Usuario(
    val id: Int = 0,
    val correo: String,
    val clave: String, //contraseña
    val productos: List<Producto>
)