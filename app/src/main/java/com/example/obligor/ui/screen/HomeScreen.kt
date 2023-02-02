package com.example.obligor.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.obligor.domain.models.Promiser
import com.example.obligor.ui.dimens.Dimens
import com.example.obligor.utils.logXertz
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    _allPromisers: Flow<List<Promiser>>,
    _selectedPromiser: Flow<Promiser>,
    addPromiser: (String) -> Unit,
) {
    val promisers = _allPromisers.collectAsState(initial = listOf())
    val selectedPromiser = _selectedPromiser.collectAsState(initial = Promiser.EmptyPromiser)
    var increaseTextFiled = remember { mutableStateOf(TextFieldValue()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.paddingMedium),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AmountInput(leadingText = "-", onDoneAction = {
            logXertz("onDoneAction: $it")
        })
        AmountInput(leadingText = "+", onDoneAction = {
            logXertz("onDoneAction: $it")
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AmountInput(
    leadingText: String, onDoneAction: (String) -> Unit
) {
    val inputTextField = remember { mutableStateOf(TextFieldValue()) }
    val labelText = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .width(180.dp)
            .onFocusChanged {
                labelText.value = if (!it.isFocused && inputTextField.value.text.isEmpty()) {
                    "0"
                } else {
                    ""
                }
            },
        value = inputTextField.value,
        label = {
            Text(text = labelText.value)
        },
        onValueChange = { decreaseTF ->
            inputTextField.value = decreaseTF
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus(force = true)
                onDoneAction.invoke(inputTextField.value.text)
            }
        ),
        leadingIcon = {
            Text(text = leadingText)
        },
        trailingIcon = {
            Text(text = "")
        },
        singleLine = true
    )
}