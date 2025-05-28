package com.ucb.despensa.productos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ucb.despensa.navigation.Screen
import java.io.Serializable
import androidx.lifecycle.viewmodel.compose.viewModel

data class Producto(
    val nombre: String,
    val cantidad: Int,
    val fechaVencimiento: String
) : Serializable

@Composable
fun ProductoItem(producto: Producto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFA0D5DC).copy(alpha = 0.9f)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Nombre: ${producto.nombre}", fontWeight = FontWeight.Bold)
            Text("Cantidad: ${producto.cantidad}")
            Text("Vence: ${producto.fechaVencimiento}")
        }
    }
}

@Composable
fun ProductosUI(
    navController: NavController,
    viewModel: ProductosViewModel = viewModel()
) {
    val productos = viewModel.productos

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2EBF2))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MiDespensa",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(40.dp),
                color = Color(0xFF004D40)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                items(productos, key = { it.nombre }) { producto ->
                    ProductoItem(producto)
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color(0xAAFFFFFF))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { navController.navigate(Screen.AgregarScreen.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
            ) {
                Text("Agregar")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductosPreview() {
    ProductosUI(navController = rememberNavController())
}
