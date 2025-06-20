package com.ucb.despensa.usuario.registrar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.usecases.Usuario.RegistrarUsuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrarViewModel constructor(
    private val registrarUsuario: RegistrarUsuario
) : ViewModel() {

    private val _registroExitoso = MutableStateFlow<Boolean?>(null)
    val registroExitoso: StateFlow<Boolean?> = _registroExitoso

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun registrar(correo: String, password: String) {
        viewModelScope.launch {
            try {
                val resultado: Boolean = registrarUsuario.invoke(correo, password)
                _registroExitoso.value = resultado
                _errorMessage.value = if (resultado) null else "No se pudo registrar"
            } catch (e: Exception) {
                _registroExitoso.value = false
                _errorMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    fun resetRegistroState() {
        _registroExitoso.value = null
        _errorMessage.value = null
    }
}
