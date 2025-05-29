package com.ucb.despensa.productos.actualizar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ucb.despensa.productos.Producto
import com.ucb.despensa.productos.ProductosViewModel

@Composable
fun ActualizarUI(
    navController: NavController,
    viewModel: ProductosViewModel = viewModel()
) {
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val productoParaEditar = savedStateHandle?.get<Producto>("productoParaEditar")

    var nombre by remember { mutableStateOf(TextFieldValue(productoParaEditar?.nombre ?: "")) }
    var cantidad by remember { mutableStateOf(TextFieldValue(productoParaEditar?.cantidad?.toString() ?: "")) }
    var fecha by remember { mutableStateOf(TextFieldValue(productoParaEditar?.fechaVencimiento ?: "")) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2EBF2))
            .padding(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Editar Producto",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40),
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = "Ingrese el producto que desea editar:",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it },
                label = { Text("Cantidad") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha de Vencimiento") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            Button(
                onClick = {
                    val productoEditado = Producto(
                        nombre.text,
                        cantidad.text.toIntOrNull() ?: 0,
                        fecha.text
                    )
                    viewModel.editarProducto(productoEditado)
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Cambios", color = Color.White)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductosPreview() {
    ActualizarUI(navController = rememberNavController())
}
