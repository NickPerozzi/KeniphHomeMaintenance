package com.example.keniphhomemaintenance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.keniphhomemaintenance.navigation.Navigation
import com.example.keniphhomemaintenance.ui.theme.KeniphHomeMaintenanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeniphHomeMaintenanceTheme {
                Surface(color = MaterialTheme.colors.background) {
                }
                Navigation()
            }
            }
        }
    }


