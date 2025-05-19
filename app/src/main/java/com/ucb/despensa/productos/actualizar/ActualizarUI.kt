package com.ucb.despensa.productos.actualizar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.setValue
import com.ucb.despensa.navigation.Screen
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ucb.despensa.productos.eliminar.EliminarUI

data class Producto(
    val nombre: String,
    val cantidad: Int,
    val fechaVencimiento: String
)

@Composable
fun ActualizarUI(navController: NavController) {
    // Simula la lista recuperada, normalmente la pasas o la recuperas del navController
    var productos: List<Producto> by remember {
        mutableStateOf(
            listOf()
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2EBF2))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Editar Productos",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF004D40),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(productos) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFA0D5DC).copy(alpha = 0.9f)
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Nombre: ${producto.nombre}", fontWeight = FontWeight.Bold)
                                Text("Cantidad: ${producto.cantidad}")
                                Text("Vence: ${producto.fechaVencimiento}")
                            }
                            IconButton(
                                onClick = {
                                    // Navega a la pantalla de edición pasando el producto seleccionado
                                    navController.currentBackStackEntry?.savedStateHandle?.set("productoParaEditar", producto)
                                    navController.navigate(Screen.EditarScreen.route)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Editar producto",
                                    tint = Color(0xFF00796B)
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.ProductosScreen.route) {
                        popUpTo(Screen.EliminarScreen.route) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Volver a Productos", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActualizarPreview() {
    ActualizarUI(navController = rememberNavController())
}