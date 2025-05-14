import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.ucb.despensa.productos.EditarProductoUI

@Composable
fun ProductosUI(navController: NavController) {
    val cantProducts = 13

    Box(
        modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(
                colors = listOf(Color(0xFFB2EBF2), Color(0xFFE0F7FA))
            )
        ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "MiDespensaApp",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40),
                modifier = Modifier.padding(20.dp)
            )

            if (cantProducts > 0) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(cantProducts) { i ->
                        var cantidad by remember { mutableStateOf(1) }

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Producto #${i + 1}",
                                modifier = Modifier.weight(1f).padding(20.dp)
                            )
                            TextField(
                                modifier = Modifier.width(40.dp),
                                value = cantidad.toString(),
                                onValueChange = {
                                    cantidad = it.toIntOrNull() ?: cantidad
                                }
                            )
                            OutlinedButton(
                                onClick = { navController.navigate("editarProducto")},
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color(0xFF00796B),
                                    contentColor = Color.White
                                ),
                                modifier=Modifier.padding(20.dp)
                            ) {
                                Text("Editar")
                            }
                        }
                        if(i==cantProducts-1){
                            Box(modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ){
                                OutlinedButton(
                                    onClick = {navController.navigate("agregarProducto")},
                                    modifier = Modifier.padding(bottom=50.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        containerColor = Color(0xFF00796B),
                                        contentColor = Color.White
                                    ),

                                ) {
                                    Text("Añadir más productos")
                                }
                            }
                        }
                    }

                }

            } else {
                Text(
                    text = "Tu despensa está vacía",
                    modifier = Modifier,
                    fontSize = 20.sp
                )
                OutlinedButton(
                    modifier = Modifier.padding(10.dp),
                    onClick = {navController.navigate("agregarProducto")},
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFF00796B),
                        contentColor = Color.White
                    ),
                ) {
                    Text("Añadir productos")
                }
            }
        }
    }
}