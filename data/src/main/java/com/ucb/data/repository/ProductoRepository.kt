package com.ucb.data.repository

import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Producto

interface ProductoRepository {
    suspend fun insertarProducto(producto: Producto): NetworkResult<Unit>
    suspend fun obtenerProductos(): NetworkResult<List<Producto>>
    suspend fun eliminarProducto(id: Int): NetworkResult<Unit>
    suspend fun actualizarProducto(producto: Producto) : NetworkResult<List<Producto>>
}