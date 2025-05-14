package com.ucb.domain

data class Producto(
    val id: Int = 0,
    val nombre: String,
    val cantidad: Int,
    val unidad: String,
    val fechaVencimiento: String
)