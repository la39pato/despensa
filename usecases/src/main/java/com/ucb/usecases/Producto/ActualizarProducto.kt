package com.ucb.usecases.Producto

import com.ucb.data.repository.ProductoRepository
import com.ucb.domain.Producto

class ActualizarProducto(private val repo: ProductoRepository) {
    suspend operator fun invoke(producto: Producto) {
        repo.actualizarProducto(producto)
    }
}
