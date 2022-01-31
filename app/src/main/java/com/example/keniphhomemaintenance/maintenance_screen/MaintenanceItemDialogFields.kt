package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_TITLE_AND_DUE_DATE_PADDING

@Composable
fun ListItemBones(maintenanceItem: MaintenanceItem) {
    Column {
        Text(
            text = maintenanceItem.title,
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(MAINTENANCE_TITLE_AND_DUE_DATE_PADDING))
        Text(
            text = maintenanceItem.dueDate,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2
        )
        Text(
            text = maintenanceItem.location,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2

        )
    }
}
