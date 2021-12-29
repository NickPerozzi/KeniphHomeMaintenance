package com.example.keniphhomemaintenance.ui.cookbook

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun KeniphOutlinedTextField(
    textMutableState: MutableState<String>,
    textLabel: String,
    focusManager: FocusManager,
    isThereAnotherTextFieldAfter: Boolean = false) =

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = textMutableState.value,
        onValueChange = { textMutableState.value = it },
        label = { Text(textLabel) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            if (isThereAnotherTextFieldAfter) {
                focusManager.moveFocus(FocusDirection.Down)
            } else {
                focusManager.clearFocus()
            }
        })
    )
