package com.example.keniphhomemaintenance.dwellings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keniphhomemaintenance.Dimens
import com.example.keniphhomemaintenance.R
import com.example.keniphhomemaintenance.ui.cookbook.KeniphKeyboardActions
import com.example.keniphhomemaintenance.ui.cookbook.KeniphOutlinedTextField

@Composable
fun NewDwellingDialog(
    visibilityState: MutableState<Boolean>,
) {

    val newName: MutableState<String> = remember { mutableStateOf("") }
    val newAddress: MutableState<String> = remember { mutableStateOf("") }
    val dwellingsViewModel: DwellingsViewModel = viewModel()

    Dialog(onDismissRequest = {})
    {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(MaterialTheme.colors.background)
                .padding(Dimens.DIALOG_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.new_dwelling),
                fontSize = Dimens.NEW_DWELLING_TEXT_SIZE,
                textAlign = TextAlign.Center
            )
            Divider()

            KeniphOutlinedTextField(
                textMutableState = newName,
                textLabel = stringResource(R.string.dwelling_name),
                KeniphKeyboardActions.NEXT
            )

            KeniphOutlinedTextField(
                textMutableState = newAddress,
                textLabel = stringResource(R.string.dwelling_address)
            )

            Button(
                modifier = Modifier
                    .padding(Dimens.BUTTON_PADDING),
                onClick = {
                    if (newName.value == "") {
                        newName.value = "Blank name. Oops!"
                    }
                    if (newAddress.value == "") {
                        newAddress.value = "Blank address. Oops!"
                    }
                    val createdDwelling = Dwelling(
                        newName.value,
                        newAddress.value
                    )
                    dwellingsViewModel.addDwelling(createdDwelling)
                    visibilityState.value = false
                }
            ) {
                Text(text = stringResource(R.string.add_dwelling))
            }
        }
    }
}