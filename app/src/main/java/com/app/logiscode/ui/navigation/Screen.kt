package com.app.logiscode.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Main : Screen("main")
    data object Map : Screen("map")
    data object Reportes : Screen("reportes")
    data object Novedades : Screen("novedades")
    data object Configuracion : Screen("configuracion")
}
