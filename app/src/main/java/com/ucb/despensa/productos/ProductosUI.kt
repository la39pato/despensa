package com.ucb.despensa.productos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.ucb.domain.Producto
import java.io.Serializable

/*
data class Producto(
    val nombre: String,
    val cantidad: Int,
    val fechaVencimiento: String
) : Serializable
*/

@Composable
fun ProductoItem(producto: Producto, navController: NavController) {
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
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Nombre: ${producto.nombre}", fontWeight = FontWeight.Bold)
                Text("Cantidad: ${producto.cantidad}")
                Text("Vence: ${producto.fechaVencimiento}")
            }/*
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { navController.navigate(Screen.EditarScreen.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {/*
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = Color.Red
                    )*/
                }
            }*/
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
            .fillMaxHeight(0.95f)
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
                    ProductoItem(producto, navController)
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AgregarScreen.route) },
                containerColor = Color(0xFF00796B)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar", tint=Color.White)
            }
            FloatingActionButton(
                onClick = { navController.navigate(Screen.EliminarScreen.route) },
                containerColor = Color(0xFF00796B)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar", tint=Color.White)
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun ProductosPreview() {
    ProductosUI(navController = rememberNavController())
}