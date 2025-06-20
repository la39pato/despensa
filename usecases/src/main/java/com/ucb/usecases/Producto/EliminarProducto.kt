package com.ucb.usecases.Producto

import com.ucb.data.repository.IProductoRepository
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Producto
class EliminarProducto(private val repo: IProductoRepository) {
    suspend operator fun invoke(id: String) = repo.eliminarProducto(id)
}