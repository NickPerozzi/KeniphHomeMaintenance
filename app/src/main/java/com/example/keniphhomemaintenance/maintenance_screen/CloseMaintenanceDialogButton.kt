package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CloseMaintenanceDialog(isDialogOpen: MutableState<Boolean>, itemTitle: MutableState<String>, dueDate: MutableState<String>, location: MutableState<String>, stringResource: Int, addMaintenanceItem: Boolean, maintenanceViewModel: MaintenanceViewModel = viewModel()) {
    ExtendedFloatingActionButton(
        onClick = {
            when (addMaintenanceItem) {
                true -> maintenanceViewModel.addMaintenanceItem(
                    itemTitle.value,
                    dueDate.value,
                    location.value
                )
            }
            isDialogOpen.value = false
            itemTitle.value = ""
            dueDate.value = ""
            location.value = ""
        },
        text = { Text(text = stringResource(id = stringResource)) }
    )
}