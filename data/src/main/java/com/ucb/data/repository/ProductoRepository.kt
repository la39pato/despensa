package com.ucb.data.repository

import com.ucb.data.local.IProductoLocalDataSource
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Producto

class ProductoRepository(
    private val localDataSource: IProductoLocalDataSource
){

    suspend fun insertar(producto: Producto): NetworkResult<Unit> {
        return if (localDataSource.insertar(producto)) {
            NetworkResult.Success(Unit)
        } else {
            NetworkResult.Error("Error al insertar el producto")
        }
    }

    suspend fun obtener(): NetworkResult<List<Producto>> {
        return NetworkResult.Success(localDataSource.obtenerTodos())
    }

    suspend fun eliminar(id: Int): NetworkResult<Unit> {
        return if (localDataSource.eliminar(id)) {
            NetworkResult.Success(Unit)
        } else {
            NetworkResult.Error("Error al eliminar el producto con ID $id")
        }
    }

    suspend fun actualizar(producto: Producto): NetworkResult<Unit> {
        return if (localDataSource.actualizar(producto)) {
            NetworkResult.Success(Unit)
        } else {
            NetworkResult.Error("Error al actualizar el producto")
        }
    }
}
