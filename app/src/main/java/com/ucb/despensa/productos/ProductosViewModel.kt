package com.ucb.despensa.productos

import androidx.lifecycle.viewModelScope
import com.ucb.data.utils.NetworkResult
//import com.ucb.domain.Producto
import com.ucb.usecases.Producto.ObtenerProductos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class ProductosViewModel : ViewModel() {
    private val _productos = mutableStateListOf<Producto>()

    val productos: List<Producto> = _productos

    init {
        _productos.addAll(
            listOf(
                Producto("Arroz", 2, "15/01/2025"),
                Producto("Leche", 1, "16/05/2025"),
                Producto("Huevos", 12, "14/11/2025")
            )
        )
    }

    fun agregarProducto(producto: Producto) {
        _productos.add(producto)
    }

    fun editarProducto(productoEditado: Producto) {
        val index = _productos.indexOfFirst { it.nombre == productoEditado.nombre }
        if (index != -1) {
            _productos[index] = productoEditado
        }
    }

    fun eliminarProducto(productoAEliminar: Producto) {
        _productos.removeAll { it.nombre == productoAEliminar.nombre }
    }
}

/*
@HiltViewModel
class ProductosViewModel @Inject constructor(
    private val obtenerProductos: ObtenerProductos
) : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        viewModelScope.launch {
            when (val result = obtenerProductos()) {
                is NetworkResult.Success -> _productos.value = result.data
                is NetworkResult.Error -> _error.value = result.error
            }
        }
    }
}*/