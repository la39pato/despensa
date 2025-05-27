package com.ucb.usecases.Producto

import com.ucb.data.repository.ProductoRepository
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Producto

class EliminarProducto(
    private val repository: ProductoRepository
) {
    suspend fun invoke(id: Producto): NetworkResult<Unit> {
        return repository.eliminar(id)
    }
}