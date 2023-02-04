package com.example.obligor.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.obligor.domain.models.Promiser
import com.example.obligor.ui.dimens.Dimens
import com.example.obligor.utils.ShowCommonKeyboard
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    _allPromisers: Flow<List<Promiser>>,
    _selectedPromiser: Flow<Promiser>,
    addPromiser: (String) -> Unit,
    onPromiserCreditChange: (Double) -> Unit
) {
    val promisers = _allPromisers.collectAsState(initial = listOf())
    val selectedPromiser = _selectedPromiser.collectAsState(initial = Promiser.EmptyPromiser)

    Box(modifier = Modifier.fillMaxSize()) {
        if (!selectedPromiser.value.isPromiserEmpty()) {
            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = Dimens.paddingMedium),
                text = selectedPromiser.value.name,
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.paddingMedium),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AmountInput(
                    leadingText = "-",
                    enabled = selectedPromiser.value.name.isNotEmpty(),
                    onDoneAction = {
                        onPromiserCreditChange.invoke(-it.toDouble())
                    }
                )
                AmountInput(
                    leadingText = "+",
                    enabled = selectedPromiser.value.name.isNotEmpty(),
                    onDoneAction = {
                        onPromiserCreditChange.invoke(it.toDouble())
                    }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            if (selectedPromiser.value.isPromiserEmpty()) {
                AddFirstObligorComponent(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onDoneAction = {
                        addPromiser.invoke(it)
                    }
                )
            } else {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = "${selectedPromiser.value.credit}₽",
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AmountInput(
    leadingText: String,
    enabled: Boolean = true,
    onDoneAction: (String) -> Unit,
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
        enabled = enabled,
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
                inputTextField.value = TextFieldValue()
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

@Composable
private fun AddFirstObligorComponent(
    modifier: Modifier = Modifier,
    onDoneAction: (String) -> Unit,
) {
    val shouldShowKeyboard = remember {
        mutableStateOf(false)
    }
    val newObligorName = remember { mutableStateOf(TextFieldValue()) }

    if (shouldShowKeyboard.value) {
        ShowCommonKeyboard(
            newObligorName,
            imeAction = ImeAction.Done,
            onImeAction = {
                with(newObligorName.value) {
                    if (text.isNotEmpty()) {
                        onDoneAction.invoke(text)
                    }
                }
            },
            focusListener = {
                shouldShowKeyboard.value = it
            }
        )
    }
    Column(modifier = modifier) {
        Text(
            text = "Please, Add your first obligor! ${newObligorName.value.text}",
        )
        Spacer(modifier = Modifier.height(Dimens.paddingSmall))
        IconButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                shouldShowKeyboard.value = true
            }
        ) {
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = "Add obligor button",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}