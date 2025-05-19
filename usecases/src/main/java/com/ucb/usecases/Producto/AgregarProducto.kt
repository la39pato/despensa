package com.ucb.usecases.Producto

import com.ucb.data.repository.ProductoRepository
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Producto

class AgregarProducto(
    private val repository: ProductoRepository
) {
    suspend fun invoke(producto: Producto): NetworkResult<Unit> {
        return repository.insertar(producto)
    }
}