package com.example.keniphhomemaintenance

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.keniphhomemaintenance.dwellings.DwellingsScreenView
import com.example.keniphhomemaintenance.ui.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.DwellingsScreen.route) {
        composable(route = Screen.DwellingsScreen.route) {
            DwellingsScreenView(navController = navController)
        }
        composable(
            route = Screen.UtilitiesScreen.route + "/{name}" + "/{address}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Default Name"
                    nullable = true
                }, navArgument("address") {
                    type = NavType.StringType
                    defaultValue = "Default Address"
                    nullable = true
                }
            )
        ) { entry ->
            UtilitiesScreen(
                name = entry.arguments?.getString("name"),
                address = entry.arguments?.getString("address")
            )
        }
    }
}