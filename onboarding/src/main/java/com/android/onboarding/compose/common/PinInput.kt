package com.android.onboarding.compose.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PinInput(pinValue: String, onPinValueChanged: (String) -> Unit) {
    val focusRequesters = remember { Array(4) { FocusRequester() } }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        repeat(4) { index ->
            TextField(
                value = pinValue.getOrNull(index)?.toString() ?: "",
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        val newPinValue = pinValue.toMutableList()
                        if (newValue.isEmpty()) {
                            // Remove the character at the specified index if newValue is empty
                            newPinValue.getOrNull(index)?.let {
                                newPinValue.removeAt(index)
                            }
                            // Move focus to the previous TextField if the current TextField is not the first one
                            if (index > 0) {
                                focusRequesters[index - 1].requestFocus()
                            }
                        } else {
                            // If newValue is not empty, update the character at the specified index
                            if (newPinValue.size > index) {
                                newPinValue[index] = newValue[0]
                            } else {
                                // Append the new value if it exceeds the size of pinValue
                                newPinValue.add(newValue[0])
                            }
                            // Move focus to the next TextField if the current TextField is not the last one
                            if (index < 3) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        }
                        onPinValueChanged(newPinValue.joinToString(separator = ""))
                    }
                },
                modifier = Modifier
                    .width(50.dp)
                    .height(55.dp).testTag("PinInputTextField${index}")
                    .focusRequester(focusRequesters[index]), // Use the corresponding FocusRequester
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp), // Adjust text size here
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done,
                    autoCorrect = false
                ),
                singleLine = true,
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
