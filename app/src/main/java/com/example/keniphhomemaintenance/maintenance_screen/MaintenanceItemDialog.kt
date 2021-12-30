package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_ITEM_CARD_TITLE_TEXT_SIZE
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_ITEM_DIALOG_CORNER_RADIUS
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_ITEM_DIALOG_LOCATION_FIELD_BOTTOM_PADDING
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_ITEM_DIALOG_PADDING_BETWEEN_TEXT_FIELDS
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_ITEM_DIALOG_TITLE_BOTTOM_PADDING
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_ITEM_DIALOG_TOP_PADDING
import com.example.keniphhomemaintenance.Dimens.MAINTENANCE_ITEM_DIALOG_VERTICAL_PADDING
import com.example.keniphhomemaintenance.R

@Composable
fun NewMaintenanceItemDialog(
    isDialogOpen: MutableState<Boolean>,
    maintenanceViewModel: MaintenanceViewModel = viewModel()
) {
    val itemTitle = remember { mutableStateOf("") }
    val dueDate = remember { mutableStateOf("") }
    val location = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { isDialogOpen.value = false }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = MAINTENANCE_ITEM_DIALOG_VERTICAL_PADDING),
            shape = RoundedCornerShape(MAINTENANCE_ITEM_DIALOG_CORNER_RADIUS),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.padding(MAINTENANCE_ITEM_DIALOG_TOP_PADDING),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = stringResource(R.string.maintenance_item_dialog_title),
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = MAINTENANCE_ITEM_CARD_TITLE_TEXT_SIZE
                )
                Spacer(modifier = Modifier.padding(MAINTENANCE_ITEM_DIALOG_TITLE_BOTTOM_PADDING))

                MaintenanceTextField(
                    fieldValue = itemTitle,
                    fieldLabel = R.string.task_name_field_label,
                    placeholder = R.string.maintenance_item_dialog_task_name_field_placeholder,
                    focusAction = FocusOptions.Down
                )

                Spacer(modifier = Modifier.padding(MAINTENANCE_ITEM_DIALOG_PADDING_BETWEEN_TEXT_FIELDS))

                MaintenanceTextField(
                    fieldValue = dueDate,
                    fieldLabel = R.string.due_date_field_label,
                    placeholder = R.string.maintenance_item_dialog_due_date_field_placeholder,
                    focusAction = FocusOptions.Down
                )

                Spacer(modifier = Modifier.padding(MAINTENANCE_ITEM_DIALOG_PADDING_BETWEEN_TEXT_FIELDS))

                MaintenanceTextField(
                    fieldValue = location,
                    fieldLabel = R.string.location_field_label,
                    placeholder = R.string.maintenance_item_dialog_location_field_placeholder,
                    focusAction = FocusOptions.Clear
                )

                Spacer(modifier = Modifier.padding(MAINTENANCE_ITEM_DIALOG_LOCATION_FIELD_BOTTOM_PADDING))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                        ExtendedFloatingActionButton(
                            onClick = {
                                onMaintenanceItemDialogClose(
                                    isDialogOpen = isDialogOpen,
                                    itemTitle = itemTitle,
                                    dueDate = dueDate,
                                    location = location
                                )
                            },
                            text = { Text(text = stringResource(id = R.string.maintenance_item_dialog_cancel_button_text)) }
                        )

                    ExtendedFloatingActionButton(
                        onClick = {
                            maintenanceViewModel.addMaintenanceItem(itemTitle.value, dueDate.value, location.value)
                            onMaintenanceItemDialogClose(
                                isDialogOpen = isDialogOpen,
                                itemTitle = itemTitle,
                                dueDate = dueDate,
                                location = location
                            )
                        },
                        text = { Text(text = stringResource(id = R.string.maintenance_item_dialog_add_item_button_text)) }
                    )

                }
            }
        }
    } 
}

fun onMaintenanceItemDialogClose(isDialogOpen: MutableState<Boolean>, itemTitle: MutableState<String>, dueDate: MutableState<String>, location: MutableState<String>) {
    isDialogOpen.value = false
    itemTitle.value = ""
    dueDate.value = ""
    location.value = ""
}

