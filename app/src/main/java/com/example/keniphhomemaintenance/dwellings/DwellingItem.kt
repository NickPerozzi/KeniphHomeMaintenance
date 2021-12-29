package com.example.keniphhomemaintenance.dwellings

import androidx.compose.foundation.layout.Column
// import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keniphhomemaintenance.ui.Screen

val dwellingsViewModel = DwellingsViewModel()

@Composable
fun DwellingItem(dwelling: Dwelling, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
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
                    Text(text = "See details")
                }
            }
        }
    }
}

/*
@Composable
fun NewDwellingItem() {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color(0xFFAAFFAA),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .clickable { dwellingsViewModel.addDwelling(Dwelling(1,"a",DwellingType.House,"b")) }
            ) {
                Text(text = "New Dwelling", style = typography.h4)
            }
        }
    }
}*/
