package com.example.keniphhomemaintenance.dwellings

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DwellingsViewModel : ViewModel() {

    private val placeholderDwelling1: Dwelling =
        Dwelling(
            1,
            "My home",
            "420 Blazit Blvd., Rantoul, IL 61866"
        )
    private val placeholderDwelling2: Dwelling = Dwelling(
        2,
        "Tenant complex",
        "80085 Mayju Look., Rantoul, IL 61866"
    )
    val dwellingsList: MutableState<List<Dwelling>> = mutableStateOf(
        listOf(
            placeholderDwelling1,
            placeholderDwelling2
        )
    )

    fun addDwelling(dwelling: Dwelling) {
        dwellingsList.value += dwelling
    }

}