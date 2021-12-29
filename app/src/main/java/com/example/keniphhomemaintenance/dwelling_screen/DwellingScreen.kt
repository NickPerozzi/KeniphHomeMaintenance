package com.example.keniphhomemaintenance.dwelling_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.keniphhomemaintenance.navigation.Screen

@Composable
fun DwellingScreen(navController: NavController) {
    var dwellingName by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp, 300.dp)
    ) {
        TextField(
            value = dwellingName,
            onValueChange = { dwellingName = it },
            placeholder = { Text("Enter a dwelling name") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate(Screen.MaintenanceListScreen.withArgs(dwellingName))
            },
            modifier = Modifier.align(Alignment.End)

        ) {
            Text(text = "Add Dwelling")
        }
    }
}

