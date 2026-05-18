package com.app.logiscode.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Map : Screen("map")
}
