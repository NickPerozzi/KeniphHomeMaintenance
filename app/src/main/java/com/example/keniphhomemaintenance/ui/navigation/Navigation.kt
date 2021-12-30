package com.example.keniphhomemaintenance.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.keniphhomemaintenance.R
import com.example.keniphhomemaintenance.UtilitiesScreen
import com.example.keniphhomemaintenance.dwellings.DwellingsNavArgParameters
import com.example.keniphhomemaintenance.dwellings.DwellingsScreenView

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
            route = Screen.UtilitiesScreen.route + "/{name}" + "/{address}",
            arguments = listOf(
                navArgument(DwellingsNavArgParameters.DWELLING_NAME) {
                    type = NavType.StringType
                    defaultValue = defaultDwellingName
                    nullable = true
                }, navArgument(DwellingsNavArgParameters.DWELLING_ADDRESS) {
                    type = NavType.StringType
                    defaultValue = defaultDwellingAddress
                    nullable = true
                }
            )
        ) { entry ->
            UtilitiesScreen(
                name = entry.arguments?.getString(DwellingsNavArgParameters.DWELLING_NAME),
                address = entry.arguments?.getString(DwellingsNavArgParameters.DWELLING_ADDRESS)
            )
        }
    }
}