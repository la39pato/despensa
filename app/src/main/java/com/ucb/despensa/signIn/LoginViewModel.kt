package com.ucb.despensa.signIn

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
    private val iniciarSesion: IniciarSesion,
) : ViewModel() {

    private val _loginSuccess = MutableStateFlow<Boolean?>(null)
    val loginSuccess: StateFlow<Boolean?> = _loginSuccess

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun login(correo: String, password: String) {
        viewModelScope.launch {
            val result = iniciarSesion(correo, password)
            _loginSuccess.value = result
            if (!result) _error.value = "Correo o contraseña incorrectos"
        }
    }
}
