package com.ucb.framework.Productos

import com.ucb.data.repository.ProductoRepository
import com.ucb.data.utils.NetworkResult
import com.ucb.data.utils.DataError
import com.ucb.domain.Producto

class ProductRepositoryImpl(private val dao: ProductDao) : ProductoRepository {

    override suspend fun insertarProducto(producto: Producto): NetworkResult<Unit> {
        return try {
            dao.insert(producto.toEntity())
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error(DataError.Unknown(e.message ?: "Error al insertar producto"))
        }
    }

    override suspend fun obtenerProductos(): NetworkResult<List<Producto>> {
        return try {
            val productos = dao.getAll().map { it.toDomain() }
            NetworkResult.Success(productos)
        } catch (e: Exception) {
            NetworkResult.Error(DataError.Unknown(e.message ?: "Error al obtener productos"))
        }
    }

    override suspend fun eliminarProducto(id: Int): NetworkResult<Unit> {
        return try {
            val producto = dao.getById(id)
            if (producto != null) {
                dao.delete(producto)
                NetworkResult.Success(Unit)
            } else {
                NetworkResult.Error(DataError.Unknown("Producto no encontrado"))
            }
        } catch (e: Exception) {
            NetworkResult.Error(DataError.Unknown(e.message ?: "Error al eliminar producto"))
        }
    }

    override suspend fun actualizarProducto(producto: Producto): NetworkResult<List<Producto>> {
        return try {
            dao.insert(producto.toEntity()) // Room lo actualiza si ya existe
            val productosActualizados = dao.getAll().map { it.toDomain() }
            NetworkResult.Success(productosActualizados)
        } catch (e: Exception) {
            NetworkResult.Error(DataError.Unknown(e.message ?: "Error al actualizar producto"))
        }
    }
}
