package com.ucb.despensa.navigation

sealed class Screen(val route: String) {
    object InicioScreen: Screen("inicio")
    object RegistrarScreeen: Screen("registrar")
    object LoginScreen: Screen("login")
    object ProductosScreen: Screen("productos")
}