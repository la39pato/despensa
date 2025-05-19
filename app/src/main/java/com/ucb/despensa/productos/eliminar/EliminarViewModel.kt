package com.ucb.despensa.productos.eliminar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Producto
import com.ucb.usecases.Producto.EliminarProducto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EliminarViewModel @Inject constructor(
    private val eliminarProducto: EliminarProducto
) : ViewModel() {

    fun eliminar(producto: Producto) {
        viewModelScope.launch {
            eliminarProducto.invoke(producto)
        }
    }
}
