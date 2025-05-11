package com.ucb.despensa.navigation

import ProductosUI
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucb.despensa.inicio.InicioUi

import com.ucb.despensa.signIn.LoginUi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.InicioScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }

    ) {
        composable(Screen.InicioScreen.route) {
            InicioUi(navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginUi(navController)
        }
        composable(Screen.ProductosScreen.route) {
            ProductosUI()
        }
    }
}
