package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_ITEM_LIST_BOTTOM_PADDING

@Composable
fun MaintenanceItemsList(maintenanceItems: List<MaintenanceItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = MAINTENANCE_ITEM_LIST_BOTTOM_PADDING.dp)
    ) {
        items(maintenanceItems) { maintenanceItems ->
            MaintenanceCard(maintenanceItems)
        }
    }
}