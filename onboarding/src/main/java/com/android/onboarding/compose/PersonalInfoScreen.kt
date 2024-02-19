package com.android.onboarding.compose

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.navigation.NavigationItem
import com.android.onboarding.R
import com.android.onboarding.compose.common.Validator.validateTelephoneNumber
import com.android.onboarding.compose.common.onBoardingBottomTextButton
import com.android.onboarding.vm.PersonalInfoVM

@Composable
fun PersonalInfoScreen(navController: NavHostController, personalInfoVM: PersonalInfoVM) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        val context = LocalContext.current
        val keyboardFocusManager = LocalFocusManager.current
        val firstNameTextField = personalInfoVM.firstNameTextFieldState.value
        val lastNameTextField = personalInfoVM.lastNameTextFieldState.value
        val telephoneNumberTextField = personalInfoVM.telephoneNumberTextFieldState.value
        var isTelephoneNumberValid by rememberSaveable { mutableStateOf(true) }

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TextField for first name
            var firstNameFocusState by remember { mutableStateOf(false) }
            TextField(
                value = firstNameTextField,
                onValueChange = { newValue ->
                    personalInfoVM.onFirstNameQueryChanged(newValue)
                },
                label = {
                    Text(
                        color = Color(0xFFC0C0CF),
                        text = if (firstNameFocusState || firstNameTextField.isNotEmpty()) "" else stringResource(
                            id = R.string.label_enter_firstName
                        ),
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    autoCorrect = false
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focus ->
                        firstNameFocusState = focus.isFocused
                    },
                keyboardActions = KeyboardActions(onDone = { keyboardFocusManager.clearFocus() }),
                visualTransformation = VisualTransformation.None,
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // TextField for last name
            var lastNameFocusState by remember { mutableStateOf(false) }
            TextField(
                value = lastNameTextField,
                onValueChange = { newValue ->
                    personalInfoVM.onLastNameQueryChanged(newValue)
                },
                label = {
                    Text(
                        color = Color(0xFFC0C0CF),
                        text = if (lastNameFocusState || lastNameTextField.isNotEmpty()) "" else stringResource(
                            id = R.string.label_enter_lastName
                        ),
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    autoCorrect = false
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focus ->
                        lastNameFocusState = focus.isFocused
                    },
                keyboardActions = KeyboardActions(onDone = { keyboardFocusManager.clearFocus() }),
                visualTransformation = VisualTransformation.None,
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // TextField for telephone number
            var telephoneNumberFocusState by remember { mutableStateOf(false) }
            TextField(
                value = telephoneNumberTextField,
                onValueChange = { newValue ->
                    personalInfoVM.onTelephoneNumberQueryChanged(newValue)
                    isTelephoneNumberValid = validateTelephoneNumber(newValue)
                },
                label = {
                    Text(
                        color = Color(0xFFC0C0CF),
                        text = if (telephoneNumberFocusState || telephoneNumberTextField.isNotEmpty()) "" else stringResource(
                            id = R.string.label_enter_telephone_number
                        ),
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done,
                    autoCorrect = false
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focus ->
                        telephoneNumberFocusState = focus.isFocused
                    },
                keyboardActions = KeyboardActions(onDone = {
                    isTelephoneNumberValid = validateTelephoneNumber(telephoneNumberTextField)
                    keyboardFocusManager.clearFocus()
                }),
                visualTransformation = VisualTransformation.None,
                singleLine = true,
                isError = isTelephoneNumberValid,
                supportingText = {
                    if (!isTelephoneNumberValid) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.alert_invalid_telephone_format),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                trailingIcon = {
                    if (!isTelephoneNumberValid)
                        Icon(Icons.Filled.Info, "error", tint = MaterialTheme.colorScheme.error)
                },
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
                if (personalInfoVM.firstNameTextFieldState.value.isNotEmpty()
                    && personalInfoVM.lastNameTextFieldState.value.isNotEmpty()
                    && personalInfoVM.telephoneNumberTextFieldState.value.isNotEmpty()
                ) {
                    if(isTelephoneNumberValid)
                        navController.navigate(NavigationItem.NEW_PIN.route)
                    else
                        Toast.makeText(context, R.string.alert_invalid_telephone_format, Toast.LENGTH_SHORT)
                            .show()
                } else
                    Toast.makeText(context, R.string.alert_empty_name_telephone, Toast.LENGTH_SHORT)
                        .show()
            }
        )

        // Handle back button press
        BackHandler(onBack = {
            navController.popBackStack()
        })
    }
}