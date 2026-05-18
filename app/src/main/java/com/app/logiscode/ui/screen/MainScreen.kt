package com.app.logiscode.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Assessment
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MainScreen(onLogout: () -> Unit) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }

    val tabs = listOf(
        TabItem("Inicio", Icons.Outlined.Home, Icons.Filled.Home),
        TabItem("Mapa", Icons.Outlined.Map, Icons.Filled.Map),
        TabItem("Reportes", Icons.Outlined.Assessment, Icons.Filled.Assessment),
        TabItem("Novedades", Icons.Outlined.Notifications, Icons.Filled.Notifications),
        TabItem("Ajustes", Icons.Outlined.Settings, Icons.Filled.Settings)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = {
                            Icon(
                                imageVector = if (selectedTab == index) tab.filledIcon else tab.outlinedIcon,
                                contentDescription = tab.label
                            )
                        },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                0 -> InicioScreen()
                1 -> MapScreen()
                2 -> ReportesScreen()
                3 -> NovedadesScreen()
                4 -> ConfiguracionScreen(onLogout = onLogout)
            }
        }
    }
}

private data class TabItem(
    val label: String,
    val outlinedIcon: ImageVector,
    val filledIcon: ImageVector
)
