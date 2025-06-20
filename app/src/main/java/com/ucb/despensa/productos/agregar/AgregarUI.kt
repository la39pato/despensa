package com.ucb.despensa.productos.agregar

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ucb.domain.Producto
import com.ucb.despensa.NotificationHelper
import com.ucb.despensa.productos.ProductosViewModel

@Composable
fun AgregarUI(
    navController: NavController,
    viewModel: ProductosViewModel = viewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    val context = LocalContext.current

    // ✅ Launcher para pedir permiso de notificación (Android 13+)
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Mostrar notificación al conceder permiso
            NotificationHelper.showNotification(
                context,
                "Producto agregado",
                "¡Tu producto se guardó exitosamente!"
            )
        }
    }

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
                text = "Agregar Producto",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = "Agrega Productos a tu inventario:",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del producto") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it },
                label = { Text("Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text("Fecha de vencimiento") },
                placeholder = { Text("dd/mm/aaaa") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (nombre.isNotBlank() && cantidad.isNotBlank() && fecha.isNotBlank()) {
                        val producto = Producto(
                            id = "", // Firestore asigna ID automáticamente
                            nombre = nombre,
                            cantidad = cantidad.toIntOrNull() ?: 0,
                            fechaVencimiento = fecha
                        )
                        viewModel.agregarProducto(producto)

                        // ✅ Manejo de notificación y permiso
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                            if (ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.POST_NOTIFICATIONS
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                // Permiso ya concedido
                                NotificationHelper.showNotification(
                                    context,
                                    "Producto agregado",
                                    "¡Tu producto se guardó exitosamente!"
                                )
                            } else {
                                // Pedir permiso
                                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            }
                        } else {
                            // Android < 13, notificación directa
                            NotificationHelper.showNotification(
                                context,
                                "Producto agregado",
                                "¡Tu producto se guardó exitosamente!"
                            )
                        }

                        // ✅ Navega atrás después de guardar
                        navController.popBackStack()

                    } else {
                        Toast.makeText(
                            context,
                            "Completa todos los campos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B))
            ) {
                Text("Guardar", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun AgregarUIPreview() {
    // Para preview no se necesita navController real
    // ⚠️ Este preview ignora funcionalidades reales
}
