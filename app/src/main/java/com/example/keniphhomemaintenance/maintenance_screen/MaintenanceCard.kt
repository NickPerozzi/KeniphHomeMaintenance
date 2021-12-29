package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_CARD_ROW_START_PADDING
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_CARD_ROW_VERTICAL_PADDING
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_CARD_START_PADDING
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_IMAGE_END_PADDING
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_IMAGE_SIZE
import com.example.keniphhomemaintenance.R

@Composable
fun MaintenanceCard(maintenanceItem: MaintenanceItem) {
    Spacer(modifier = Modifier.width(MAINTENANCE_CARD_START_PADDING.dp))
    Row(
        modifier = Modifier
            .padding(vertical = MAINTENANCE_CARD_ROW_VERTICAL_PADDING.dp)
            .padding(start = MAINTENANCE_CARD_ROW_START_PADDING.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val isLightTheme = MaterialTheme.colors.isLight
        Image(
            painter = painterResource(
                id =
                if (isLightTheme) {
                    R.drawable.ic_baseline_build_24_light
                } else {
                    R.drawable.ic_baseline_build_24_dark
                }
            ),
            contentDescription = stringResource(id = R.string.maintenance_item_type_image),
            modifier = Modifier
                .size(MAINTENANCE_IMAGE_SIZE.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(MAINTENANCE_IMAGE_END_PADDING.dp))

        ListItemBones(maintenanceItem = maintenanceItem)
    }
}

