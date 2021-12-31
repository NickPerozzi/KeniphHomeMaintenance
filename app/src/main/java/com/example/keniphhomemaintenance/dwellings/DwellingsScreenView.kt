package com.example.keniphhomemaintenance.dwellings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.keniphhomemaintenance.Dimens
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
            .background(MaterialTheme.colors.onSurface),
        backgroundColor = MaterialTheme.colors.onSurface,
        floatingActionButton = {
            KeniphFloatingActionButton(onClick = { isDwellingDialogVisible.value = true })
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        if (isDwellingDialogVisible.value) {
            NewDwellingDialog(isDwellingDialogVisible)
        }
    }
    LazyColumn(
        modifier = Modifier
            .padding(bottom = Dimens.LAZY_COLUMN_BOTTOM_PADDING)
            .fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = Dimens.LAZY_COLUMN_HOR_CONTENT_PADDING,
            vertical = Dimens.LAZY_COLUMN_VERT_CONTENT_PADDING
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
