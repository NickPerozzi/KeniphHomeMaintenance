package com.example.keniphhomemaintenance.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.keniphhomemaintenance.dwellings.DwellingItem
import com.example.keniphhomemaintenance.dwellings.DwellingsViewModel
import com.example.keniphhomemaintenance.dwellings.NewDwellingItem

val dwellingsViewModel = DwellingsViewModel()

@ExperimentalComposeUiApi
@Composable
fun DwellingsScreenView(navController: NavController) {

    ConstraintLayout {

        val (lazyColumn, scaffold) = createRefs()

        val listOfDwellings = dwellingsViewModel.dwellingsList.value

        LazyColumn(
            modifier = Modifier.constrainAs(lazyColumn) {
                top.linkTo(parent.top)
                bottom.linkTo(scaffold.top)
            },
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = listOfDwellings,
                itemContent = {
                    DwellingItem(dwelling = it, navController)
                }
            )
        }

        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .constrainAs(scaffold) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.size(50.dp),
                    shape = CircleShape,
                    onClick = { dwellingsViewModel.addDwelling() }) {
                    Icon(Icons.Default.Add, contentDescription = "Button to add a dwelling")
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) {}

    }
}