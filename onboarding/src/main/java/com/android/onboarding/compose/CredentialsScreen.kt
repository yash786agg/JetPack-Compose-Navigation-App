package com.android.onboarding.compose

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.navigation.NavigationItem
import com.android.onboarding.R
import com.android.onboarding.compose.common.DataStorePreferences.EMAIL
import com.android.onboarding.compose.common.DataStorePreferences.dataStore
import com.android.onboarding.compose.common.DataStorePreferences.setValue
import com.android.onboarding.compose.common.Validator.validateEmail
import com.android.onboarding.compose.common.onBoardingBottomTextButton
import com.android.onboarding.vm.CredentialsVM
import kotlinx.coroutines.launch

@Composable
fun CredentialsScreen(navController: NavHostController, credentialsVM: CredentialsVM) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        val keyboardFocusManager = LocalFocusManager.current
        val emailTextField = credentialsVM.emailTextFieldState.value
        val passwordTextFieldState = credentialsVM.passwordTextFieldState.value
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        var isEmailValid by rememberSaveable { mutableStateOf(true) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Take up available vertical space equally with the next Box
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // TextField for email
                var emailFocusState by remember { mutableStateOf(false) }
                TextField(
                    value = emailTextField,
                    onValueChange = { newValue ->
                        credentialsVM.onEmailQueryChanged(newValue)
                        isEmailValid = validateEmail(newValue)
                    },
                    label = {
                        Text(
                            color = Color(0xFFC0C0CF),
                            text = if (emailFocusState || emailTextField.isNotEmpty()) "" else stringResource(
                                id = R.string.label_enter_email_address
                            ),
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done,
                        autoCorrect = false
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focus ->
                            emailFocusState = focus.isFocused
                        },
                    keyboardActions = KeyboardActions(onDone = {
                        isEmailValid = validateEmail(emailTextField)
                        keyboardFocusManager.clearFocus()
                    }),
                    visualTransformation = VisualTransformation.None,
                    singleLine = true,
                    isError = isEmailValid,
                    supportingText = {
                        if (!isEmailValid) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.alert_invalid_enter_address_format),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    trailingIcon = {
                        if (!isEmailValid)
                            Icon(Icons.Filled.Info, "error", tint = MaterialTheme.colorScheme.error)
                    },
                )

                // Spacer between the TextField components
                Spacer(modifier = Modifier.height(24.dp))

                // TextField for password
                var passwordFocusState by remember { mutableStateOf(false) }
                TextField(
                    value = passwordTextFieldState,
                    onValueChange = { newValue ->
                        credentialsVM.onPasswordQueryChanged(newValue)
                    },
                    label = {
                        Text(
                            color = Color(0xFFC0C0CF),
                            text = if (passwordFocusState || passwordTextFieldState.isNotEmpty()) "" else stringResource(
                                id = R.string.label_enter_password
                            ),
                            softWrap = false
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                        autoCorrect = false
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focus ->
                            passwordFocusState = focus.isFocused
                        },
                    trailingIcon = {
                        val image = if (passwordVisible)
                            ImageVector.vectorResource(R.drawable.ic_eye_visible_black_24dp)
                        else ImageVector.vectorResource(R.drawable.ic_eye_visibleoff_black_24dp)

                        // Please provide localized description for accessibility services
                        val description =
                            if (passwordVisible) stringResource(id = R.string.text_hide_password) else stringResource(
                                id = R.string.text_show_password
                            )

                        IconButton(onClick = {
                            passwordVisible = !passwordVisible
                        }) {
                            Icon(
                                imageVector = image,
                                tint = Color.White,
                                contentDescription = description,
                            )
                        }
                    },
                )
            }
        }

        // Spacer to push the next element (onBoardingBottomTextButton) to the bottom
        Spacer(modifier = Modifier.weight(1f))

        // Bottom button
        onBoardingBottomTextButton(
            onBackBtnClick = {
                navController.popBackStack()
            },
            onNextBtnClick = {
                if (credentialsVM.emailTextFieldState.value.isNotEmpty()
                    && credentialsVM.passwordTextFieldState.value.isNotEmpty()
                ) {
                    if (isEmailValid) {
                        coroutineScope.launch {
                            dataStore?.setValue(EMAIL, credentialsVM.emailTextFieldState.value)
                        }
                        navController.navigate(NavigationItem.PERSONAL_INFO.route)
                    } else {
                        Toast.makeText(
                            context,
                            R.string.alert_invalid_enter_address_format,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                } else
                    Toast.makeText(context, R.string.alert_empty_email_password, Toast.LENGTH_SHORT)
                        .show()
            }
        )

        BackHandler(onBack = {
            // Navigate back to the previous screen
            navController.popBackStack()
        })
    }
}