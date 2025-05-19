package com.ucb.despensa.usuario.registrar

import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateListOf
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
import com.ucb.despensa.navigation.Screen


@Composable
fun RegistrarUI(navController: NavController) {
    val usuariosRegistrados = remember { mutableStateListOf<Usuario>() }

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var registroExitoso by remember { mutableStateOf<Boolean?>(null) }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color(0xFFB2EBF2)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Regístrate",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF004D40),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Validación básica simple
                if (nombre.isNotBlank() && correo.isNotBlank() && password.isNotBlank()) {
                    val existe = usuariosRegistrados.any { it.correo == correo }
                    if (!existe) {
                        usuariosRegistrados.add(Usuario(nombre, correo, password))
                        registroExitoso = true
                        showError = false
                    } else {
                        registroExitoso = false
                        showError = true
                    }
                } else {
                    registroExitoso = false
                    showError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
        ) {
            Text("Registrarse", color = Color.White)
        }

        if (showError) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (registroExitoso == false && usuariosRegistrados.any { it.correo == correo })
                    "Correo ya registrado"
                else
                    "Por favor llena todos los campos",
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (registroExitoso == true) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Registro exitoso! Ahora puedes iniciar sesión.",
                color = Color.Green,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                navController.navigate(Screen.LoginScreen.route) {
                    popUpTo("registroScreen") { inclusive = true }
                }
            }) {
                Text("Ir a Iniciar Sesión")
            }
        }
    }
}


data class Usuario(
    val nombre: String,
    val correo: String,
    val password: String
)

@Preview(showBackground = true)
@Composable
fun RegistrarUIPreview() {
    RegistrarUI(navController = NavController(LocalContext.current))
}


