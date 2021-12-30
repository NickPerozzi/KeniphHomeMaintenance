package com.example.keniphhomemaintenance.ui.cookbook

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.keniphhomemaintenance.Dimens
import com.example.keniphhomemaintenance.R

@Composable
fun KeniphFloatingActionButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
            modifier = Modifier.size(Dimens.FLOATING_ACTION_BUTTON_SIZE),
    shape = CircleShape,
    onClick = {
        onClick()
    }) {
        Icon(
            Icons.Default.Add,
            contentDescription = stringResource(R.string.content_description_floating_action_button)
        )
    }
}