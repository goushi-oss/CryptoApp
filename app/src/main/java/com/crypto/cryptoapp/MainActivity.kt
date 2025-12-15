package com.crypto.cryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.crypto.cryptoapp.ui.navigation.NavGraph
import com.crypto.cryptoapp.ui.theme.CryptoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTheme {
                NavGraph(navController = rememberNavController())
            }
        }
    }
}

