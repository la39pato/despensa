package com.ucb.data.local

import com.ucb.domain.Producto

interface IProductoLocalDataSource {
    suspend fun insertar(producto: Producto): Boolean
    suspend fun obtenerTodos(): List<Producto>
    suspend fun eliminar(id: Producto): Boolean
    suspend fun actualizar(producto: Producto): Boolean
}