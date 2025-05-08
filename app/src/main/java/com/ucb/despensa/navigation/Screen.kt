package com.ucb.despensa.navigation

sealed class Screen(val route: String) {
    object InicioScreen: Screen("inicio")
    object LoginScreen: Screen("login")
}