package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.keniphhomemaintenance.R

class MaintenanceViewModel: ViewModel() {

    val maintenanceItemList: MutableState<List<MaintenanceItem>> = mutableStateOf(listOf(
        MaintenanceItem(R.drawable.ic_baseline_build_24, "Change Air Filter", "Due date: 1/5/22", "Located at: Basement"),
        MaintenanceItem(R.drawable.ic_baseline_build_24, "Break Microwave", "Due date: 6/9/22", "Located at: Kitchen"),
        MaintenanceItem(R.drawable.ic_baseline_build_24, "Buy New Microwave", "Due date: 6/4/22", "Located at: Walmart?")
    ))

    fun addMaintenanceItem(itemTitle: String, dueDate: String, location: String) {
        maintenanceItemList.value += MaintenanceItem(R.drawable.ic_baseline_build_24, itemTitle, dueDate, location)
    }

}

