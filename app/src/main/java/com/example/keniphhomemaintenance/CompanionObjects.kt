package com.example.keniphhomemaintenance

import com.example.keniphhomemaintenance.dwellings.Dwelling

class CompanionObjects {

    companion object {
        const val PLACEHOLDER_ID = 0
        fun getDefaultDwellings(): List<Dwelling> = listOf(
            Dwelling(1, "My home", "1337 Street Lane, Rantoul, IL 61866"),
            Dwelling(2, "Tenant complex", "789 Mayju Look., Rantoul, IL 61866")
        )
    }

}