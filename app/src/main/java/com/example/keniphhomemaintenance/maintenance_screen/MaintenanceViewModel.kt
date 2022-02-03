package com.example.keniphhomemaintenance.maintenance_screen

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keniphhomemaintenance.database.DwellingWithMaintenanceItems
import com.example.keniphhomemaintenance.database.KeniphDatabase
import com.example.keniphhomemaintenance.database.KeniphRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MaintenanceViewModel(context: Context): ViewModel() {

    private val repository: KeniphRepository
    val maintenanceItemsList: MutableState<List<MaintenanceItem>> = mutableStateOf( emptyList() )

    init {
        val dwellingsDao = KeniphDatabase.getKeniphDatabase(context).dwellingsDao()
        repository = KeniphRepository(dwellingsDao)
    }

    fun addMaintenanceItem(newMaintenanceItem: MaintenanceItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMaintenanceItemToDb(newMaintenanceItem)
            maintenanceItemsList.value += newMaintenanceItem
        }
    }

    fun getMaintenanceItems(dwellingID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            maintenanceItemsList.value = parseMaintenanceItems( repository.getMaintenanceItemsForDwellingFromDb(dwellingID) )
        }
    }

    private fun parseMaintenanceItems(maintenanceCollection: List<DwellingWithMaintenanceItems>): List<MaintenanceItem> {
        val maintenanceItems: MutableList<MaintenanceItem> = mutableListOf()
        for (task in maintenanceCollection[0].maintenanceItems) {
            maintenanceItems.add( task )
        }
        return maintenanceItems.toList()
    }

}

