package com.app.logiscode.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.logiscode.ui.screen.ConfiguracionScreen
import com.app.logiscode.ui.screen.HomeScreen
import com.app.logiscode.ui.screen.LoginScreen
import com.app.logiscode.ui.screen.MapScreen
import com.app.logiscode.ui.screen.NovedadesScreen
import com.app.logiscode.ui.screen.ReportesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        composable(Screen.Map.route) {
            MapScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Reportes.route) {
            ReportesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Novedades.route) {
            NovedadesScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.Configuracion.route) {
            ConfiguracionScreen(onBack = { navController.popBackStack() })
        }
    }
}
