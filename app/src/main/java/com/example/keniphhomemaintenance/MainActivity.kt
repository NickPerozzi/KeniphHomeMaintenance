package com.example.keniphhomemaintenance

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.keniphhomemaintenance.maintenance_screen.MaintenanceCard
import com.example.keniphhomemaintenance.maintenance_screen.MaintenanceItem
import com.example.keniphhomemaintenance.navigation.Navigation
import com.example.keniphhomemaintenance.ui.theme.KeniphHomeMaintenanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeniphHomeMaintenanceTheme {
                Surface(color = MaterialTheme.colors.background) {
                }
                Configuration.UI_MODE_NIGHT_YES
            }
            Navigation()
            }

        }


    }


