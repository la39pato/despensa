package com.ucb.usecases.Producto

import com.ucb.data.repository.ProductoRepository
import com.ucb.data.utils.NetworkResult

class EliminarProducto(
    private val repository: ProductoRepository
) {
    suspend fun invoke(id: Int): NetworkResult<Unit> {
        return repository.eliminar(id)
    }
}