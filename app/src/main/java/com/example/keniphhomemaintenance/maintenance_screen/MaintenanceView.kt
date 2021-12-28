package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.keniphhomemaintenance.R

val maintenanceViewModel = MaintenanceViewModel()

@Composable
fun MaintenanceScreen() {
    val maintenanceList = maintenanceViewModel.maintenanceItemList.value
    val isDialogOpen = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(dimensionResource(id = R.dimen.add_maintenance_item_button_size)),
                shape = CircleShape,
                onClick = {
                    isDialogOpen.value = true
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Button to add a maintenance item")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ){
            ShowDialog(isDialogOpen)
    }

    MaintenanceItemsList(maintenanceList)

}



@Composable
fun MaintenanceCard(maintenanceItem: MaintenanceItem) {
    Spacer(modifier = Modifier.width(dimensionResource(R.dimen.maintenance_card_start_padding)))
    Row(
        modifier = Modifier
            .padding(vertical = dimensionResource(R.dimen.maintenance_card_row_vertical_padding))
            .padding(start = dimensionResource(R.dimen.maintenance_card_row_start_padding))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_baseline_build_24), 
            contentDescription = "Maintenance Item Type",
            modifier = Modifier
                .size(dimensionResource(R.dimen.maintenance_image_size))
                .clip(CircleShape)
        )
        
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.maintenance_image_end_padding)))

        Column {
            Text(
                text = maintenanceItem.title,
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.maintenance_title_and_due_date_padding)))
            Text(
                text = maintenanceItem.dueDate,
                color = Color.Black,
                style = MaterialTheme.typography.body2
            )
            Text(
                text = maintenanceItem.location,
                color = Color.Black,
                style = MaterialTheme.typography.body2

            )
        }
    }
}

@Composable
fun MaintenanceItemsList(maintenanceItems: List<MaintenanceItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = dimensionResource(R.dimen.maintenance_item_list_bottom_padding))
    ) {
        items(maintenanceItems) { maintenanceItems ->
            MaintenanceCard(maintenanceItems)
        }
    }
}

@Composable
fun ShowDialog(isDialogOpen: MutableState<Boolean>) {
    val itemTitle = remember { mutableStateOf("") }
    val dueDate = remember { mutableStateOf("") }
    val location = remember { mutableStateOf("") }

    if (isDialogOpen.value) {
        Dialog(
            onDismissRequest = { isDialogOpen.value = false }) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = dimensionResource(R.dimen.maintenance_item_dialog_vertical_padding)),
              shape = RoundedCornerShape(dimensionResource(R.dimen.maintenance_item_dialog_corner_radius)),
                color = Color.DarkGray
            ) {
                val focusManager = LocalFocusManager.current
                Column(
                    modifier = Modifier.padding(dimensionResource(R.dimen.maintenance_item_dialog_top_padding)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = stringResource(R.string.maintenance_item_dialog_title),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.maintenance_item_dialog_title_bottom_padding)))
                    
                    OutlinedTextField(
                        value = itemTitle.value,
                        onValueChange = { itemTitle.value = it },
                        label = { Text(text = "Task Title") },
                        placeholder = { Text(text = stringResource(id = R.string.maintenance_item_dialog_task_name_field_placeholder)) },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.secondaryVariant,
                            unfocusedBorderColor = MaterialTheme.colors.secondaryVariant,
                            textColor = Color.White,
                            cursorColor = Color.White,
                            focusedLabelColor = MaterialTheme.colors.secondaryVariant,
                            unfocusedLabelColor = Color.Gray,
                            placeholderColor = Color.Gray,
                            disabledBorderColor = Color.Gray
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.moveFocus(FocusDirection.Down) }
                            ),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text)
                    )

                    Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.maintenance_item_dialog_padding_between_text_fields)))

                    OutlinedTextField(
                        value = dueDate.value,
                        onValueChange = { dueDate.value = it },
                        label = { Text(text = "Due Date") },
                        placeholder = { Text(text = stringResource(R.string.maintenance_item_dialog_due_date_field_placeholder)) },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.secondaryVariant,
                            unfocusedBorderColor = MaterialTheme.colors.secondaryVariant,
                            textColor = Color.White,
                            cursorColor = Color.White,
                            focusedLabelColor = MaterialTheme.colors.secondaryVariant,
                            unfocusedLabelColor = Color.Gray,
                            placeholderColor = Color.Gray,
                            disabledBorderColor = Color.Gray
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text)
                    )

                    Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.maintenance_item_dialog_padding_between_text_fields)))

                    OutlinedTextField(
                        value = location.value,
                        onValueChange = { location.value = it },
                        label = { Text(text = "Location") },
                        placeholder = { Text(text = stringResource(id = R.string.maintenance_item_dialog_location_field_placeholder)) },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.secondaryVariant,
                            unfocusedBorderColor = MaterialTheme.colors.secondaryVariant,
                            textColor = Color.White,
                            cursorColor = Color.White,
                            focusedLabelColor = MaterialTheme.colors.secondaryVariant,
                            unfocusedLabelColor = Color.Gray,
                            placeholderColor = Color.Gray,
                            disabledBorderColor = Color.Gray
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text)
                    )

                    Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.maintenance_item_dialog_location_field_bottom_padding)))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ExtendedFloatingActionButton(
                            onClick = { isDialogOpen.value = false
                                itemTitle.value = ""
                                dueDate.value = ""
                                location.value = ""
                                      },
                            text = { Text(text = stringResource(id = R.string.maintenance_item_dialog_cancel_button_text)) }
                        )

                        ExtendedFloatingActionButton(
                            onClick = {
                                maintenanceViewModel.addMaintenanceItem(itemTitle.value, dueDate.value, location.value)
                                isDialogOpen.value = false
                                itemTitle.value = ""
                                dueDate.value = ""
                                location.value = ""
                                      },
                            text = { Text(text = stringResource(id = R.string.maintenance_item_dialog_add_item_button_text)) }
                        )
                    }
                }
            }
        }
    }

}