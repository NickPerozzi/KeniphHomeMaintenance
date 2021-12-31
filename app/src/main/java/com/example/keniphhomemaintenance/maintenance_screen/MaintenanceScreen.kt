package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keniphhomemaintenance.ui.cookbook.KeniphFloatingActionButton

@Composable
fun MaintenanceScreen(name: String, address: String, maintenanceViewModel: MaintenanceViewModel = viewModel()) {
    val maintenanceList = maintenanceViewModel.maintenanceItemList.value
    val isDialogOpen = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            KeniphFloatingActionButton(onClick = { isDialogOpen.value = true })
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        if (isDialogOpen.value) {
            NewMaintenanceItemDialog(isDialogOpen)
        }
    }

    MaintenanceItemsList(maintenanceList)

}

