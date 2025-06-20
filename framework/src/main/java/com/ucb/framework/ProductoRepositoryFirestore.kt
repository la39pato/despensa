package com.ucb.framework

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ucb.data.repository.IProductoRepository
import com.ucb.domain.Producto
import kotlinx.coroutines.tasks.await

class ProductoRepositoryFirestore : IProductoRepository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private fun getProductosRef() =
        firestore.collection("usuarios")
            .document(auth.currentUser!!.uid)
            .collection("productos")

    // ✅ AGREGAR CON LOG
    override suspend fun agregarProducto(producto: Producto): Boolean {
        return try {
            val docRef = getProductosRef().add(
                mapOf(
                    "nombre" to producto.nombre,
                    "cantidad" to producto.cantidad,
                    "fechaVencimiento" to producto.fechaVencimiento
                )
            ).await()

            Log.d("REPO", "✅ AGREGADO: ID=${docRef.id} nombre=${producto.nombre}")

            true
        } catch (e: Exception) {
            Log.e("REPO", "❌ Error al agregar: ${e.localizedMessage}")
            false
        }
    }

    // ✅ OBTENER TODOS CON LOG
    override suspend fun obtenerProductos(): List<Producto> {
        return try {
            val snapshot = getProductosRef().get().await()

            val lista = snapshot.documents.map { doc ->
                Producto(
                    id = doc.id,
                    nombre = doc.getString("nombre") ?: "",
                    cantidad = doc.getLong("cantidad")?.toInt() ?: 0,
                    fechaVencimiento = doc.getString("fechaVencimiento") ?: ""
                )
            }

            Log.d("REPO", "✅ OBTENIDOS: ${lista.size} productos")

            lista
        } catch (e: Exception) {
            Log.e("REPO", "❌ Error al obtener: ${e.localizedMessage}")
            emptyList()
        }
    }

    // ✅ ACTUALIZAR CON LOG
    override suspend fun actualizarProducto(producto: Producto): Boolean {
        return try {
            getProductosRef().document(producto.id).set(
                mapOf(
                    "nombre" to producto.nombre,
                    "cantidad" to producto.cantidad,
                    "fechaVencimiento" to producto.fechaVencimiento
                )
            ).await()

            Log.d("REPO", "✅ ACTUALIZADO: ID=${producto.id}")

            true
        } catch (e: Exception) {
            Log.e("REPO", "❌ Error al actualizar: ${e.localizedMessage}")
            false
        }
    }

    // ✅ ELIMINAR CON LOG
    override suspend fun eliminarProducto(id: String): Boolean {
        return try {
            getProductosRef().document(id).delete().await()

            Log.d("REPO", "✅ ELIMINADO: ID=$id")

            true
        } catch (e: Exception) {
            Log.e("REPO", "❌ Error al eliminar: ${e.localizedMessage}")
            false
        }
    }
}
