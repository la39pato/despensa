package com.ucb.despensa.productos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun EditarProductoUI(navController : NavController){
    Box(
        modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(
                colors = listOf(Color(0xFFB2EBF2), Color(0xFFE0F7FA))
            )
        ),
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = "Editar Producto",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40),
                modifier = Modifier.padding(20.dp)
            )

            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically){
                var nombre by remember { mutableStateOf("Producto") }


                Text(
                    text = "Nombre: ",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF004D40),
                    modifier = Modifier.padding(20.dp)
                )
                TextField(
                    modifier = Modifier.width(40.dp),
                    value = "_",
                    onValueChange = {nuevoNombre->nombre=nuevoNombre }
                )
            }
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically){
                var cantidad by remember { mutableStateOf("Producto") }
                Text(
                    text = "Cantidad: ",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF004D40),
                    modifier = Modifier.padding(20.dp)
                )
                TextField(
                    modifier = Modifier.width(40.dp),
                    value = "Producto",
                    onValueChange = {nuevaCantidad->cantidad=nuevaCantidad }
                )
            }
            OutlinedButton(
                onClick = {},
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF00796B),
                    contentColor = Color.White
                ),
                modifier=Modifier.padding(20.dp)
            ) {
                Text("Guardar")
            }
        }
    }
}