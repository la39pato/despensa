package com.ucb.despensa.usuario.iniciosesion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.usecases.Usuario.IniciarSesion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val iniciarSesion: IniciarSesion
) : ViewModel() {

    // Estado para indicar si el login fue exitoso o no (null = aún sin intento)
    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState

    fun login(usuario: String, password: String) {
        viewModelScope.launch {
            val exito = iniciarSesion.invoke(usuario, password)
            _loginState.value = exito
        }
    }

   fun resetLoginState() {
        _loginState.value = null
    }
}

