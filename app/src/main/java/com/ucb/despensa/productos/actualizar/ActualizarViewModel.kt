package com.ucb.despensa.productos.actualizar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Producto
import com.ucb.usecases.Producto.ActualizarProducto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActualizarViewModel(
    private val actualizarProducto: ActualizarProducto
) : ViewModel() {

    private val _state = MutableStateFlow<NetworkResult<Unit>?>(null)
    val state: StateFlow<NetworkResult<Unit>?> = _state

    fun actualizar(producto: Producto) {
        viewModelScope.launch {
            _state.value = NetworkResult.Success(Unit)
            val resultado = actualizarProducto.invoke(producto)
            _state.value = resultado
        }
    }

    fun resetState() {
        _state.value = null
    }
}
