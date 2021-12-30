package com.example.keniphhomemaintenance.dwellings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keniphhomemaintenance.Dimens
import com.example.keniphhomemaintenance.R
import com.example.keniphhomemaintenance.ui.navigation.Screen

@Composable
fun DwellingItem(dwelling: Dwelling, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(Dimens.DWELLING_ITEM_CARD_PADDING)
            .fillMaxWidth(),
        elevation = Dimens.DWELLING_ITEM_CARD_ELEVATION,
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(Dimens.DWELLING_ITEM_COLUMN_PADDING)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = dwelling.name, style = typography.h4)
                Text(text = dwelling.address as String, style = typography.h6)
                Button(onClick = {
                    navController.navigate(
                        Screen.UtilitiesScreen.withArgs(
                            dwelling.name,
                            dwelling.address
                        )
                    )
                }) {
                    Text(text = stringResource(R.string.see_details))
                }
            }
        }
    }
}
