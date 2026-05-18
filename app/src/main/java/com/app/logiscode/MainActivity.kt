package com.app.logiscode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.app.logiscode.ui.navigation.AppNavigation
import com.app.logiscode.ui.theme.LogisCodeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogisCodeTheme {
                AppNavigation()
            }
        }
    }
}
