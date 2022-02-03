package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keniphhomemaintenance.ui.cookbook.KeniphFloatingActionButton

@Composable
fun MaintenanceScreen(dwellingID: Int, name: String, address: String, maintenanceViewModel: MaintenanceViewModel = viewModel(
    factory = MaintenanceItemsViewModelFactory(LocalContext.current)
)) {
    val maintenanceList = maintenanceViewModel.maintenanceItemsList.value
    val isDialogOpen = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        maintenanceViewModel.getMaintenanceItems(dwellingID)
    }

    Scaffold(
        floatingActionButton = {
            KeniphFloatingActionButton(onClick = { isDialogOpen.value = true })
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        if (isDialogOpen.value) {
            NewMaintenanceItemDialog(isDialogOpen, dwellingID)
        }
    }

    MaintenanceItemsList(maintenanceList)

}

