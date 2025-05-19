package com.ucb.despensa.usuario.registrar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.utils.NetworkResult
import com.ucb.domain.Usuario
import com.ucb.usecases.Usuario.RegistrarUsuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrarViewModel @Inject constructor(
    private val registrarUsuario: RegistrarUsuario
) : ViewModel() {

    private val _registroExitoso = MutableStateFlow<Boolean?>(null)
    val registroExitoso: StateFlow<Boolean?> = _registroExitoso

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun registrar(nombre: String, correo: String, password: String) {
        viewModelScope.launch {
            try {
                val usuario = Usuario(nombre = nombre, correo = correo, password = password, productos = String())
                val resultado = registrarUsuario.invoke(usuario)
                if (resultado is NetworkResult.Success && resultado.data) {
                    _registroExitoso.value = true
                    _error.value = null
                } else {
                    _registroExitoso.value = false
                    _error.value = if (resultado is NetworkResult.Error) resultado.error else "Error desconocido"

                }
            } catch (e: Exception) {
                _registroExitoso.value = false
                _error.value = "Error al registrar: ${e.message}"
            }
        }
    }
}


