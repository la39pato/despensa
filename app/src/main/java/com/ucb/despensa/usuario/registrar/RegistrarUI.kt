package com.ucb.despensa.usuario.registrar

import androidx.compose.foundation.background
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ucb.despensa.navigation.Screen

data class Usuario(
    val correo: String,
    val contrasena: String
)

@Composable
fun RegistrarUI(
    navController: NavHostController,
    onRegistroExitoso: (Usuario) -> Unit = {}
) {
    var correo by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Registrar", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") }
        )

        OutlinedTextField(
            value = contraseña,
            onValueChange = { contraseña = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val usuario = Usuario(correo, contraseña)
            // Guardar en la SavedStateHandle del backstack
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set("usuarioRegistrado", usuario)

            // Ir al Login y quitar la pantalla actual
            navController.navigate(Screen.LoginScreen.route) {
                popUpTo(Screen.RegistrarScreen.route) { inclusive = true }
            }
        }) {
            Text("Registrar")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegistrarUIPreview() {
    RegistrarUI(navController = NavHostController(LocalContext.current))
}


