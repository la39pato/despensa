package com.ucb.usecases.Producto

import com.ucb.data.repository.ProductoRepository

class EliminarProducto(private val repo: ProductoRepository) {
    suspend operator fun invoke(id: Int) {
        repo.eliminarProducto(id)
    }
}