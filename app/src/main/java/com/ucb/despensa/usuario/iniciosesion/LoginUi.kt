package com.ucb.despensa.usuario.iniciosesion
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.ucb.despensa.navigation.Screen

data class Usuario(
    val nombre: String,
    val correo: String,
    val password: String
)

@Composable
fun LoginUI(navController: NavController) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    val usuariosBase = remember {
        mutableStateListOf(
            Usuario("Nataly", "nataly@gmail.com", "12345"),
            Usuario("Daniela", "daniela@gmail.com", "1234"),
            Usuario("Camila", "camila@gmail.com", "123")
        )
    }

    val usuarioNuevo = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.get<Usuario>("usuarioRegistrado")

    LaunchedEffect(usuarioNuevo) {
        usuarioNuevo?.let {
            if (!usuariosBase.any { u -> u.correo == it.correo }) {
                usuariosBase.add(it)
                navController.currentBackStackEntry?.savedStateHandle?.remove<Usuario>("usuarioRegistrado")
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2EBF2))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40),
                modifier = Modifier.padding(bottom = 24.dp)
            )

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
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val usuarioValido = usuariosBase.find {
                        it.correo == correo && it.password == password
                    }

                    if (usuarioValido != null) {
                        mensajeError = null
                        navController.navigate(Screen.ProductosScreen.route)
                    } else {
                        mensajeError = "Correo o contraseña incorrectos"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
            ) {
                Text("Iniciar Sesión", color = Color.White)
            }

            mensajeError?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it, color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                navController.navigate(Screen.RegistrarScreeen.route)
            }) {
                Text("¿No tienes cuenta? Regístrate")
            }
        }
    }
}