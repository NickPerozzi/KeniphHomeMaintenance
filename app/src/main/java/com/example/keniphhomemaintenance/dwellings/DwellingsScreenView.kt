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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController


val addDwellingDialog = mutableStateOf(false)

@ExperimentalComposeUiApi
@Composable
fun DwellingsScreenView(navController: NavController) {

    ConstraintLayout {

        val (lazyColumn, scaffold) = createRefs()

        val listOfDwellings = dwellingsViewModel.dwellingsList.value

        Scaffold(

            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Transparent)
                .constrainAs(scaffold) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                },
            backgroundColor = Color.Transparent,
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.size(50.dp),
                    shape = CircleShape,
                    onClick = {
                        addDwellingDialog.value = true
                        // dwellingsViewModel.addDwelling()
                    }) {
                    Icon(Icons.Default.Add, contentDescription = "Button to add a dwelling")
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) {
            if (addDwellingDialog.value) {
                NewDwellingDialog()
            }
        }
        LazyColumn(
            modifier = Modifier
                .constrainAs(lazyColumn) {
                    top.linkTo(parent.top)
                }
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
}

@ExperimentalComposeUiApi
@Composable
fun NewDwellingDialog(
    modifier: Modifier = Modifier,
    newName: MutableState<String> = remember { mutableStateOf("") },
    newAddress: MutableState<String> = remember { mutableStateOf("") },
) {
    var expanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current


    val spinnerList: MutableList<DwellingType> = mutableListOf()
    for (item in DwellingType.values()) {
        spinnerList.add(item)
    }
    var selectedSpinnerItem by remember { mutableStateOf("") }
    var selectedSpinnerItemLabel: DwellingType by remember { mutableStateOf(DwellingType.Other) }
    var spinnerTextFieldSize by remember { mutableStateOf(Size.Zero) }
    val spinnerIcon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Dialog(onDismissRequest = { addDwellingDialog.value = false })
    {
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

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = newName.value,
                onValueChange = { newName.value = it },
                label = { Text("Dwelling name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    keyboardController?.hide()
                    focusRequester.requestFocus()
                })
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = newAddress.value,
                onValueChange = { newAddress.value = it },
                label = { Text("Dwelling address") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                    focusRequester.requestFocus()
                })
            )

            Column(modifier = Modifier.padding(20.dp)) {

                OutlinedTextField(
                    value = selectedSpinnerItem,
                    onValueChange = { selectedSpinnerItem = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            spinnerTextFieldSize = coordinates.size.toSize()
                        }
                        .clickable { expanded = !expanded },
                    readOnly = true,
                    label = { Text(text = "Select dwelling type") },
                    trailingIcon = {
                        Icon(
                            spinnerIcon,
                            "Spinner to select the type of housing",
                            Modifier.clickable { expanded = !expanded })
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { spinnerTextFieldSize.width.toDp() })
                ) {
                    spinnerList.forEach { label ->
                        DropdownMenuItem(onClick = {
                            selectedSpinnerItem = label.type
                            selectedSpinnerItemLabel = label
                            expanded = false
                        }) {
                            Text(text = label.type)
                        }
                    }
                }

                Button(
                    modifier = Modifier
                        .padding(16.dp),
                    onClick = {
                        val createdDwelling = Dwelling(
                            5,
                            newName.value,
                            selectedSpinnerItemLabel,
                            newAddress.value
                        )
                        dwellingsViewModel.addDwelling(createdDwelling)
                        addDwellingDialog.value = false
                    }
                ) {
                    Text(text = "Add dwelling")
                }
            }
        }
    }
}