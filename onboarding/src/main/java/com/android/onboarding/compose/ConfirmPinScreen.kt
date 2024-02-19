package com.android.onboarding.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.navigation.NavigationItem
import com.android.onboarding.R
import com.android.onboarding.compose.common.onBoardingBottomTextButton

@Composable
fun ConfirmPinScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.text_create_new_pin),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            var confirmPinValue by rememberSaveable{ mutableStateOf("") }
            BasicTextField(
                value = confirmPinValue, onValueChange = { newValue ->
                    if (newValue.length <= 4) {
                        confirmPinValue = newValue
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ), decorationBox = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        repeat(4) { index ->
                            val pinText = when {
                                index >= confirmPinValue.length -> ""
                                else -> confirmPinValue[index].toString()
                            }

                            val isFocused = confirmPinValue.length == index
                            Text(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(45.dp)
                                    .border(
                                        if (isFocused) 2.dp
                                        else 1.dp,
                                        if (isFocused) Color.Black
                                        else Color.DarkGray,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .padding(2.dp),
                                text = pinText,
                                style = MaterialTheme.typography.headlineLarge,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            )
        }

        // Spacer to push the next element (onBoardingBottomTextButton) to the bottom
        Spacer(modifier = Modifier.weight(1f))

        // Bottom button
        onBoardingBottomTextButton(
            onBackBtnClick = {
                navController.popBackStack()
            },
            onNextBtnClick = {
               // navController.navigate(NavigationItem.CONFIRM_PIN.route)
            }
        )

        BackHandler(onBack = {
            // Navigate back to the previous screen
            navController.popBackStack()
        })
    }
}