package com.ucb.framework

import android.content.Context
import com.ucb.data.local.IProductoLocalDataSource
import com.ucb.framework.Productos.toEntity
import com.ucb.framework.Productos.toModelList
import com.ucb.domain.Producto
import com.ucb.framework.Productos.ProductDao

class ProductoLocalDataSource(context: Context) : IProductoLocalDataSource {

    private val productDao: ProductDao = AppRoomDatabase.getDatabase(context).productDao()

    override suspend fun insertar(producto: Producto): Boolean {
        return try {
            productDao.insert(producto.toEntity())
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun obtenerTodos(): List<Producto> {
        return try {
            productDao.getAll().toModelList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun eliminar(id: Int): Boolean {
        return try {
            val productoDB = productDao.getById(id)
            if (productoDB != null) {
                productDao.delete(productoDB)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun actualizar(producto: Producto): Boolean {
        return try {
            productDao.insert(producto.toEntity()) // REPLACE actualiza si ya existe
            true
        } catch (e: Exception) {
            false
        }
    }
}
