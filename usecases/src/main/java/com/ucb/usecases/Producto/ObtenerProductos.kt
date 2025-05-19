package com.ucb.usecases.Producto

import com.ucb.data.repository.ProductoRepository
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Producto

class ObtenerProductos(
    private val repo: ProductoRepository
) {
    suspend operator fun invoke(): NetworkResult<List<Producto>> {
        return repo.obtener()
    }
}
