package com.ucb.despensa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ucb.despensa.ui.theme.DespensaTheme
import com.ucb.despensa.navigation.AppNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val firebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance()
        val authRepository = com.ucb.framework.Usuario.AuthRepository(firebaseAuth)
        val iniciarSesion = com.ucb.usecases.Usuario.IniciarSesion(authRepository)
        val loginViewModel = com.ucb.despensa.usuario.iniciosesion.LoginViewModel(iniciarSesion)
        val registrarUsuario = com.ucb.usecases.Usuario.RegistrarUsuario(authRepository)
        val signUpViewModel = com.ucb.despensa.usuario.registrar.RegistrarViewModel(registrarUsuario)
        setContent {
            AppNavigation(loginViewModel, signUpViewModel)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DespensaTheme {
        Greeting("Android")
    }
}