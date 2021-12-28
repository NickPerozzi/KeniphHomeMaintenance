package com.example.keniphhomemaintenance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.keniphhomemaintenance.dwelling_screen.DwellingScreen
import com.example.keniphhomemaintenance.maintenance_screen.MaintenanceScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.DwellingCreationScreen.route) {
        composable(route = Screen.DwellingCreationScreen.route) {
            DwellingScreen(navController = navController)
            
        }
        composable(
            route = Screen.MaintenanceListScreen.route + "/{dwellingName}",
            arguments = listOf(
                navArgument("dwellingName") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            MaintenanceScreen()
        }
    }
}


