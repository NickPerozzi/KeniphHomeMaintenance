package com.example.keniphhomemaintenance.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.keniphhomemaintenance.R
import com.example.keniphhomemaintenance.dwellings.DwellingsNavArgParameters
import com.example.keniphhomemaintenance.dwellings.DwellingsScreenView
import com.example.keniphhomemaintenance.maintenance_screen.MaintenanceScreen
import com.example.keniphhomemaintenance.navigation.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val defaultDwellingName = stringResource(R.string.default_dwelling_name)
    val defaultDwellingAddress = stringResource(R.string.default_dwelling_address)
    NavHost(navController = navController, startDestination = Screen.DwellingsScreen.route) {
        composable(route = Screen.DwellingsScreen.route) {
            DwellingsScreenView(navController = navController)
        }
        composable(
            route = Screen.MaintenanceListScreen.route + "/{id}" + "/{name}" + "/{address}",
            arguments = listOf(
                navArgument(DwellingsNavArgParameters.DWELLING_ID) {
                    type = NavType.IntType
                },
                navArgument(DwellingsNavArgParameters.DWELLING_NAME) {
                    type = NavType.StringType
                },
                navArgument(DwellingsNavArgParameters.DWELLING_ADDRESS) {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            MaintenanceScreen(
                dwellingID = entry.arguments?.getInt(DwellingsNavArgParameters.DWELLING_ID)  ?: 0,
                name = entry.arguments?.getString(DwellingsNavArgParameters.DWELLING_NAME) ?: defaultDwellingName,
                address = entry.arguments?.getString(DwellingsNavArgParameters.DWELLING_ADDRESS) ?: defaultDwellingAddress
            )
        }
    }
}