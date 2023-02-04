package com.example.obligor.utils

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowCommonKeyboard(
    textFieldValue: MutableState<TextFieldValue>,
    imeAction: ImeAction = ImeAction.Default,
    onImeAction: () -> Unit,
    focusListener: (Boolean) -> Unit = {},
) {
    val focusRequest = remember {
        FocusRequester()
    }
    val keyboard = LocalSoftwareKeyboardController.current

    //current workaround, probably better place on base container and create controller provider
    BasicTextField(
        modifier = Modifier
            .size(0.2.dp)
            .alpha(0f)
            .focusRequester(focusRequest)
            .onFocusChanged {
                focusListener.invoke(it.isFocused)
            },
        value = textFieldValue.value,
        onValueChange = {
            textFieldValue.value = it
        },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusRequest.freeFocus()
                keyboard?.hide()
                onImeAction.invoke()
            },
            onGo = {
                focusRequest.freeFocus()
                keyboard?.hide()
                onImeAction.invoke()
            },
            onNext = {
                focusRequest.freeFocus()
                keyboard?.hide()
                onImeAction.invoke()
            },
            onPrevious = {
                focusRequest.freeFocus()
                keyboard?.hide()
                onImeAction.invoke()
            },
            onSearch = {
                focusRequest.freeFocus()
                keyboard?.hide()
                onImeAction.invoke()
            },
            onSend = {
                focusRequest.freeFocus()
                keyboard?.hide()
                onImeAction.invoke()
            }
        )
    )
    LaunchedEffect(key1 = focusRequest) {
        focusRequest.requestFocus()
        delay(100)
        keyboard?.show()
    }
}
