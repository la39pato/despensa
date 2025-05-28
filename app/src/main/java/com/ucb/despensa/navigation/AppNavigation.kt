package com.ucb.despensa.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucb.despensa.usuario.registrar.RegistrarUI
import com.ucb.despensa.inicio.InicioUi
import com.ucb.despensa.productos.ProductosUI
import com.ucb.despensa.productos.agregar.AgregarUI
import com.ucb.despensa.productos.eliminar.EliminarUI
import com.ucb.despensa.productos.actualizar.ActualizarUI
import com.ucb.despensa.usuario.iniciosesion.LoginUI

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
            LoginUI(navController)
        }
        composable(Screen.ProductosScreen.route) {
            ProductosUI(navController)
        }
        composable(Screen.RegistrarScreen.route) {
            RegistrarUI(navController = navController)
            { nuevoUsuario ->
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("usuarioRegistrado", nuevoUsuario)
                navController.popBackStack()
            }
        }
        composable(Screen.AgregarScreen.route) {
            AgregarUI(navController)
        }
        composable(Screen.EditarScreen.route) {
            ActualizarUI(navController)
        }
        composable(Screen.EliminarScreen.route) {
            EliminarUI(navController)
        }
    }
}
