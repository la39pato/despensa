package com.ucb.despensa.navigation

sealed class Screen(val route: String) {
    object InicioScreen: Screen("inicio")
    object RegistrarScreen: Screen("registrar")
    object LoginScreen: Screen("login")
    object ProductosScreen: Screen("productos")
    object AgregarScreen: Screen("agregar")
    object EditarScreen: Screen("editar")
    object EliminarScreen: Screen("eliminar")
}