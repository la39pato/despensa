package com.ucb.usecases.Producto

import com.ucb.data.repository.IProductoRepository
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Producto

class ActualizarProducto(private val repo: IProductoRepository) {
    suspend operator fun invoke(producto: Producto) = repo.actualizarProducto(producto)
}
