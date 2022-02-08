package com.example.keniphhomemaintenance.dwellings

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.keniphhomemaintenance.Dimens
import com.example.keniphhomemaintenance.ui.cookbook.KeniphFloatingActionButton

@Composable
fun DwellingsScreenView(
    navController: NavController,
    dwellingsViewModel: DwellingsViewModel = viewModel(
        factory = DwellingsViewModelFactory(LocalContext.current)
    )
) {
    val isDwellingDialogVisible = remember { mutableStateOf(false) }
    val dwellingsList = dwellingsViewModel.dwellings.value

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = MaterialTheme.colors.background,
        floatingActionButton = {
            KeniphFloatingActionButton(onClick = { isDwellingDialogVisible.value = true })
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        if (isDwellingDialogVisible.value) {
            NewDwellingDialog(isDwellingDialogVisible)
        }
    }

    if (!dwellingsViewModel.loadedDwellingsPreviously.value) {
        LaunchedEffect(true) {
            dwellingsViewModel.getDwellingsList()
            dwellingsViewModel.loadedDwellingsPreviously.value = true
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
        dwellingsViewModel.getDwellingsList()
        items(
            items = dwellingsList,
            itemContent = {
                DwellingItem(dwelling = it, navController)
            }
        )
    }
}

