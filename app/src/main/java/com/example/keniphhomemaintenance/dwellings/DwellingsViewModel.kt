package com.example.keniphhomemaintenance.dwellings

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keniphhomemaintenance.CompanionObjects.Companion.PLACEHOLDER_ID
import com.example.keniphhomemaintenance.R
import com.example.keniphhomemaintenance.database.KeniphDatabase
import com.example.keniphhomemaintenance.database.KeniphRepository
import com.example.keniphhomemaintenance.maintenance_screen.MaintenanceItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DwellingsViewModel(context: Context) : ViewModel() {

    private val repository: KeniphRepository
    var dwellings: MutableState<List<Dwelling>> = mutableStateOf(listOf())
    val loadedDwellingsPreviously: MutableState<Boolean> = mutableStateOf(false)

    init {
        val dwellingsDao = KeniphDatabase.getKeniphDatabase(context).dwellingsDao()
        repository = KeniphRepository(dwellingsDao)
    }

    fun addDwelling(dwelling: Dwelling) {
        viewModelScope.launch(Dispatchers.IO) {
            val dwellingID = repository.addDwellingToDb(dwelling)
            prePopulateMaintenanceItems(dwellingID.toInt())
            getDwellingsList()
        }
    }

    fun getDwellingsList() {
        viewModelScope.launch(Dispatchers.IO) {
            dwellings.value = repository.getDwellingsListFromDb()
        }
    }

    private fun prePopulateMaintenanceItems(dwellingID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val maintenanceItems = getDefaultMaintenanceItems(dwellingID)
            repository.prePopulateMaintenanceItems(maintenanceItems)
        }
    }

    companion object {
        fun getDefaultMaintenanceItems(dwellingID: Int): List<MaintenanceItem> = listOf(
            MaintenanceItem(PLACEHOLDER_ID, R.drawable.ic_baseline_build_24_light, "Change Air Filter", "Due date: 7-Sep-22", "Location: Basement", dwellingID),
            MaintenanceItem(PLACEHOLDER_ID, R.drawable.ic_baseline_build_24_light, "Break Microwave", "Due date: 11-August-22", "Location: Kitchen", dwellingID),
            MaintenanceItem(PLACEHOLDER_ID, R.drawable.ic_baseline_build_24_light, "Buy a New Microwave", "Due date: 29-Sep-22", "Location: Target", dwellingID)
        )
    }
}