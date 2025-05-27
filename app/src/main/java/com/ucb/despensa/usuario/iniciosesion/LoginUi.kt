package com.ucb.despensa.usuario.iniciosesion

import android.widget.Toast
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
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

data class Usuario(
    val correo: String,
    val contrasena: String
)
object UsuarioRepo {
    val usuarios = mutableListOf<Usuario>()

    init {
        // Usuarios por defecto
        usuarios.add(Usuario("admin@correo.com", "1234"))
        usuarios.add(Usuario("demo@correo.com", "demo"))
    }

    fun agregarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
    }

    fun validarUsuario(correo: String, contrasena: String): Boolean {
        return usuarios.any {
            it.correo == correo && it.contrasena == contrasena
        }
    }
}

@Composable
fun LoginUI(navController: NavHostController,
            usuarioRegistrado: Usuario? = null) {
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val usuarioRegistrado = savedStateHandle?.get<Usuario>("usuarioRegistrado")

    var correo by remember { mutableStateOf(usuarioRegistrado?.correo ?: "") }
    var contrasena by remember { mutableStateOf(usuarioRegistrado?.contrasena ?: "") }

    val context = LocalContext.current
    var mostrarError by remember { mutableStateOf(false) }

    if (mostrarError) {
        // Lanzamos el Toast fuera del renderizado Compose
        LaunchedEffect(Unit) {
            Toast.makeText(context, "Correo o contraseña inválidos", Toast.LENGTH_SHORT).show()
            mostrarError = false
        }
    }

    Column(
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
            .padding(16.dp)
    ) {
        Text("Iniciar Sesión", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") }
        )

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (UsuarioRepo.validarUsuario(correo, contrasena)) {
                navController.navigate(Screen.ProductosScreen.route)
            } else {
                mostrarError = true
            }
        }) {
            Text("Entrar")
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
        TextButton(onClick = {
            navController.navigate(Screen.RegistrarScreen.route)
        }) {
            Text("¿No tienes cuenta? Regístrate aquí")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginUIPreview() {
    LoginUI(navController = NavHostController(LocalContext.current))
}

