package com.example.keniphhomemaintenance.ui

sealed class Screen(val route: String) {
    object DwellingsScreen: Screen("dwellings_screen")
    object UtilitiesScreen: Screen("utilities_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
