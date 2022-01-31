package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_CARD_ROW_START_PADDING
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_CARD_ROW_VERTICAL_PADDING
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_CARD_START_PADDING
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_IMAGE_END_PADDING
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_IMAGE_SIZE
import com.example.keniphhomemaintenance.R

@Composable
fun MaintenanceCard(maintenanceItem: MaintenanceItem) {
    Spacer(modifier = Modifier.width(MAINTENANCE_CARD_START_PADDING))
    Row(
        modifier = Modifier
            .padding(vertical = MAINTENANCE_CARD_ROW_VERTICAL_PADDING)
            .padding(start = MAINTENANCE_CARD_ROW_START_PADDING)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_build_24_light).orDark(R.drawable.ic_baseline_build_24_dark),
            contentDescription = stringResource(id = R.string.maintenance_item_type_image),
            modifier = Modifier
                .size(MAINTENANCE_IMAGE_SIZE)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(MAINTENANCE_IMAGE_END_PADDING))

        ListItemBones(maintenanceItem = maintenanceItem)
    }

}

@Composable
fun Painter.orDark(darkResource: Int): Painter {
    if (MaterialTheme.colors.isLight) {
        return this
    } else {
        return painterResource(darkResource)
    }
}


