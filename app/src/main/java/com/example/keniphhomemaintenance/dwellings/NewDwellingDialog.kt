package com.example.keniphhomemaintenance.dwellings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keniphhomemaintenance.R
import com.example.keniphhomemaintenance.ui.cookbook.KeniphOutlinedTextField

@Composable
fun NewDwellingDialog(
    visibilityState: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    newName: MutableState<String> = remember { mutableStateOf("") },
    newAddress: MutableState<String> = remember { mutableStateOf("") },
    dwellingsViewModel: DwellingsViewModel = viewModel()
) {

    Dialog(onDismissRequest = {})
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
            Text(text = stringResource(R.string.new_dwelling), fontSize = 20.sp, textAlign = TextAlign.Center)
            Divider()

            KeniphOutlinedTextField(
                textMutableState = newName,
                textLabel = stringResource(R.string.dwelling_name),
                focusManager = focusManager,
                true
            )

            KeniphOutlinedTextField(
                textMutableState = newAddress,
                textLabel = stringResource(R.string.dwelling_address),
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
                    visibilityState.value = false
                }
            ) {
                Text(text = stringResource(R.string.add_dwelling))
            }
        }
    }
}