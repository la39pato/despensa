package com.ucb.despensa.productos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Producto
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ucb.framework.ProductoRepositoryFirestore

class ProductosViewModel : ViewModel() {

    // ✅ Lista observable compatible con tu UI actual
    private val _productos = mutableStateListOf<Producto>()
    val productos: SnapshotStateList<Producto> = _productos

    // ✅ Repo Firestore real (instanciado manualmente)
    private val repo = ProductoRepositoryFirestore()

    init {
        cargarProductos()
    }

    // ✅ Cargar productos desde Firestore
    fun cargarProductos() {
        viewModelScope.launch {
            val lista = repo.obtenerProductos()
            _productos.clear()
            _productos.addAll(lista)
        }
    }

    // ✅ Agregar producto a Firestore y refrescar lista
    fun agregarProducto(producto: Producto) {
        Log.d("REPO", "Agregando producto: $producto")

        viewModelScope.launch {
            val exito = repo.agregarProducto(producto)
            if (exito) {
                cargarProductos()
            }
        }
    }

    // ✅ Editar producto en Firestore y refrescar lista
    fun editarProducto(productoEditado: Producto) {
        viewModelScope.launch {
            val exito = repo.actualizarProducto(productoEditado)
            if (exito) {
                cargarProductos()
            }
        }
    }

    // ✅ Eliminar producto en Firestore y refrescar lista
    fun eliminarProducto(productoAEliminar: Producto) {
        viewModelScope.launch {
            val exito = repo.eliminarProducto(productoAEliminar.id)
            if (exito) {
                cargarProductos()
            }
        }
    }
}
