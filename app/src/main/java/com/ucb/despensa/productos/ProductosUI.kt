import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import com.ucb.despensa.navigation.Screen

data class Producto(
    val nombre: String,
    val cantidad: Int,
    val fechaVencimiento: String
)

@Composable
fun ProductosUI(navController: NavController) {
    var productos by remember {
        mutableStateOf(
            listOf(
                Producto("Arroz", 2, "2025-01-15"),
                Producto("Leche", 1, "2024-12-01"),
                Producto("Huevos", 12, "2024-11-22")
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB2EBF2))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp), // espacio para footer
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MiDespensa",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(40.dp),
                color = Color(0xFF004D40)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
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
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("Nombre: ${producto.nombre}", fontWeight = FontWeight.Bold)
                            Text("Cantidad: ${producto.cantidad}")
                            Text("Vence: ${producto.fechaVencimiento}")
                        }
                    }
                }
            }
        }

        // Footer con navegación
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
            Button(
                onClick = { navController.navigate(Screen.EditarScreen.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
            ) {
                Text("Editar")
            }
            Button(
                onClick = { navController.navigate(Screen.EliminarScreen.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
            ) {
                Text("Eliminar")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductosScreenPreview() {
    ProductosUI(navController = rememberNavController())
}
