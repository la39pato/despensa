package com.ucb.despensa.usuario.iniciosesion

import android.widget.Toast
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ucb.despensa.navigation.Screen

@Composable
fun LoginUI(
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current

    val loginState by loginViewModel.loginState.collectAsState()

    // Navega o muestra error cuando cambie loginState
    LaunchedEffect(loginState) {
        when (loginState) {
            true -> {
                loginViewModel.resetLoginState()
                navController.navigate(Screen.ProductosScreen.route)
            }
            false -> {
                loginViewModel.resetLoginState()
                Toast.makeText(context, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
            else -> {
                // null → sin intento, no hacer nada
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2EBF2))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Iniciar Sesión",
            fontSize = 24.sp,
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
                loginViewModel.login(correo, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
        ) {
            Text("Iniciar Sesión", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

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
    // Ojo: el preview NO tendrá NavController real, así que ignóralo si da error
    // Este preview es solo visual
}
