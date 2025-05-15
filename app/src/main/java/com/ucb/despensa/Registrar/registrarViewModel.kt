package com.ucb.despensa.Registrar

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

    fun registrar(nombre: String, correo: String, password: String) {
        viewModelScope.launch {
            val usuario = Usuario(nombre = nombre, correo = correo, password = password, productos = String())

            val resultado = registrarUsuario(usuario)
            _registroExitoso.value = resultado is NetworkResult.Success && resultado.data == true
        }
    }
}

