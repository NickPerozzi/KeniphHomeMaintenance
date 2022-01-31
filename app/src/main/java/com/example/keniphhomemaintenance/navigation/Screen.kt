package com.example.keniphhomemaintenance.navigation

sealed class Screen(val route: String) {
    object DwellingsScreen : Screen("dwellings_screen")
    object MaintenanceListScreen : Screen("maintenance_list_screen")

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
