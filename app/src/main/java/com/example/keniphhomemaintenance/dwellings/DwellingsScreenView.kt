package com.example.keniphhomemaintenance.dwellings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.keniphhomemaintenance.ui.cookbook.KeniphOutlinedTextField


val isDwellingDialogVisible = mutableStateOf(false)

@Composable
fun DwellingsScreenView(navController: NavController) {

    val listOfDwellings = dwellingsViewModel.dwellingsList.value

    Scaffold(

        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Transparent),
        backgroundColor = Color.Transparent,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                onClick = {
                    isDwellingDialogVisible.value = true
                }) {
                Icon(Icons.Default.Add, contentDescription = "Button to add a dwelling")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        if (isDwellingDialogVisible.value) {
            NewDwellingDialog()
        }
    }
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 100.dp),

        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = listOfDwellings,
            itemContent = {
                DwellingItem(dwelling = it, navController)
            }
        )
    }
}

@Composable
fun NewDwellingDialog(
    modifier: Modifier = Modifier,
    newName: MutableState<String> = remember { mutableStateOf("") },
    newAddress: MutableState<String> = remember { mutableStateOf("") },
) {

    Dialog(onDismissRequest = { })
    {
        val focusManager = LocalFocusManager.current
        Column(
            modifier
                .wrapContentSize()
                .background(Color.LightGray)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "New dwelling!", fontSize = 20.sp, textAlign = TextAlign.Center)
            Divider()

            KeniphOutlinedTextField(
                textMutableState = newName,
                textLabel = "Dwelling name",
                focusManager = focusManager,
                true
            )

            KeniphOutlinedTextField(
                textMutableState = newAddress,
                textLabel = "Dwelling address",
                focusManager = focusManager
            )

            Button(
                modifier = Modifier
                    .padding(16.dp),
                onClick = {
                    val createdDwelling = Dwelling(
                        5,
                        newName.value,
                        newAddress.value
                    )
                    dwellingsViewModel.addDwelling(createdDwelling)
                    isDwellingDialogVisible.value = false
                }
            ) {
                Text(text = "Add dwelling")
            }
        }
    }
}