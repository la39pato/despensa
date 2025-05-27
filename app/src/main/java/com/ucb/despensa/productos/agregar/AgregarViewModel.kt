package com.ucb.despensa.productos.agregar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Producto
import com.ucb.usecases.Producto.AgregarProducto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgregarViewModel @Inject constructor(
    private val agregarProducto: AgregarProducto
) : ViewModel() {

    private val _productoAgregado = MutableStateFlow(false)
    val productoAgregado: StateFlow<Boolean> = _productoAgregado

    fun agregar(id: Int, nombre: String, cantidad: Int, unidad: String, fechavencimiento: String) {
        viewModelScope.launch {
            val producto = Producto(id, nombre, cantidad, unidad , fechavencimiento)
            val resultado = agregarProducto.invoke(producto)
            _productoAgregado.value = resultado is NetworkResult.Success
        }
    }
}
