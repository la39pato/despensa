package com.ucb.despensa.productos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Producto
import com.ucb.usecases.Producto.ObtenerProductos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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
}