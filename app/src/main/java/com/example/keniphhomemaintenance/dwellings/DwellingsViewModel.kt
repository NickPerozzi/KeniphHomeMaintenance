package com.example.keniphhomemaintenance.dwellings

import android.app.Application
import android.content.res.Resources
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class DwellingsViewModel : ViewModel() {

    val placeholderDwelling1: Dwelling =
        Dwelling(1, "My home", DwellingType.House, "420 Blazit Blvd., Rantoul, IL 61866")
    val placeholderDwelling2: Dwelling = Dwelling(
        2,
        "Tenant complex",
        DwellingType.Apartment,
        "80085 Mayju Look., Rantoul, IL 61866"
    )
    val newDwelling: Dwelling =
        Dwelling(3, "New home!", DwellingType.MobileHome, "1 Stevenson Dr., Lincolnshire, IL 60069")

    val dwellingsList: MutableState<List<Dwelling>> = mutableStateOf(
        listOf(
            placeholderDwelling1,
            placeholderDwelling2
        )
    )

    fun addDwelling() {
        dwellingsList.value += newDwelling
    }

}