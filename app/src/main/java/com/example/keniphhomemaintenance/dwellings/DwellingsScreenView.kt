package com.example.keniphhomemaintenance.dwellings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.keniphhomemaintenance.ui.cookbook.KeniphFloatingActionButton

@Composable
fun DwellingsScreenView(
    navController: NavController,
    dwellingsViewModel: DwellingsViewModel = viewModel()
) {

    val isDwellingDialogVisible = remember { mutableStateOf(false) }

    val listOfDwellings = dwellingsViewModel.dwellingsList.value

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Transparent),
        backgroundColor = Color.Transparent,
        floatingActionButton = {
            KeniphFloatingActionButton(isDwellingDialogVisible)
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        if (isDwellingDialogVisible.value) {
            NewDwellingDialog(isDwellingDialogVisible)
        }
    }
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 100.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        items(
            items = listOfDwellings,
            itemContent = {
                DwellingItem(dwelling = it, navController)
            }
        )
    }
}
