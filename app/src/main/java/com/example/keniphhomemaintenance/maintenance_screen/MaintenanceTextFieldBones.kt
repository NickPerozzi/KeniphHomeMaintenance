package com.example.keniphhomemaintenance.maintenance_screen

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun MaintenanceTextField(fieldValue: MutableState<String>, fieldLabel: Int, placeholder: Int, focusAction: FocusOptions) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = fieldValue.value,
        onValueChange = { fieldValue.value = it },
        label = { Text(text = stringResource(id = fieldLabel)) },
        placeholder = { Text(text = stringResource(id = placeholder)) },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.secondaryVariant,
            unfocusedBorderColor = MaterialTheme.colors.secondaryVariant,
            textColor = MaterialTheme.colors.onBackground,
            cursorColor = MaterialTheme.colors.onBackground,
            focusedLabelColor = MaterialTheme.colors.onBackground,
            unfocusedLabelColor = MaterialTheme.colors.onBackground,
            placeholderColor = MaterialTheme.colors.onBackground,
            disabledBorderColor = MaterialTheme.colors.onBackground
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                when(focusAction) {
                    FocusOptions.Down -> focusManager.moveFocus(FocusDirection.Down)
                    FocusOptions.Clear -> focusManager.clearFocus()
                }
            }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text)
    )
}

enum class FocusOptions {
    Down,
    Clear
}
