package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keniphhomemaintenance.Dimens.ADD_MAINTENANCE_ITEM_BUTTON_SIZE
import com.example.keniphhomemaintenance.R

@Composable
fun MaintenanceScreen(maintenanceViewModel: MaintenanceViewModel = viewModel()) {
    val maintenanceList = maintenanceViewModel.maintenanceItemList.value
    val isDialogOpen = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size( ADD_MAINTENANCE_ITEM_BUTTON_SIZE),
                shape = CircleShape,
                onClick = {
                    isDialogOpen.value = true
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.new_maintenance_item_button))
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        if (isDialogOpen.value) {
            NewMaintenanceItemDialog(isDialogOpen)
        }
    }

    MaintenanceItemsList(maintenanceList)

}

