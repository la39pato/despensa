package com.ucb.despensa.productos.eliminar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ucb.despensa.navigation.Screen
import com.ucb.despensa.productos.ProductosViewModel

@Composable
fun EliminarUI(
    navController: NavController,
    viewModel: ProductosViewModel = viewModel()
) {
    val productos = viewModel.productos

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2EBF2))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Eliminar Productos",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(40.dp),
                color = Color(0xFF004D40)
            )
            Text(
                text = "Elimine el Producto que ya no desee que esté en su inventario:",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(productos, key = { it.id }) { producto ->  // ✅ Usa el ID como key
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFD0F0F0), shape = RoundedCornerShape(8.dp))
                            .padding(16.dp)
                    ) {
                        Column {
                            Text("Nombre: ${producto.nombre}", fontWeight = FontWeight.Bold)
                            Text("Cantidad: ${producto.cantidad}")
                            Text("Vence: ${producto.fechaVencimiento}")
                        }

                        IconButton(
                            onClick = {
                                viewModel.eliminarProducto(producto)
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(x = (-8).dp, y = (8).dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Eliminar",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

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
fun EliminarPreview() {
    EliminarUI(navController = rememberNavController())
}
