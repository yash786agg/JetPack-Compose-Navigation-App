package com.android.onboarding.compose

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.android.onboarding.compose.common.onBoardingBottomTextButton
import com.android.onboarding.vm.CredentialsVM

@Composable
fun CredentialsScreen(navController: NavHostController,credentialsVM: CredentialsVM) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val keyboardFocusManager = LocalFocusManager.current
            val emailLoginTextField = credentialsVM.emailLoginTextFieldState.value
            val passwordLoginTextFieldState = credentialsVM.passwordLoginTextFieldState.value
            var passwordVisible by rememberSaveable { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp)
            ) {
                var focusState by remember { mutableStateOf(false) }
                TextField(value = emailLoginTextField,
                    onValueChange = { newValue ->
                        credentialsVM.onEmailQueryChanged(newValue)
                    }, label = {
                        Text(
                            color = Color(0xFFC0C0CF),
                            text = if (focusState || emailLoginTextField.isNotEmpty()) "" else stringResource(
                                id = R.string.label_enter_email_address
                            ),
                        )
                    }, keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done,
                        autoCorrect = false
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focus ->
                            focusState = focus.isFocused
                        },
                    keyboardActions = KeyboardActions(onDone = { keyboardFocusManager.clearFocus() }),
                    visualTransformation = VisualTransformation.None,
                    singleLine = true,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp)
            ) {
                var focusState by remember { mutableStateOf(false) }
                TextField(
                    value = passwordLoginTextFieldState,
                    onValueChange = { newValue ->
                        credentialsVM.onPasswordQueryChanged(newValue)
                    },
                    label = {
                        Text(
                            color = Color(0xFFC0C0CF),
                            text = if (focusState || passwordLoginTextFieldState.isNotEmpty()) "" else stringResource(
                                id = R.string.label_enter_password
                            ),
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
                            focusState = focus.isFocused
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

        Spacer(modifier = Modifier.weight(1f))

        onBoardingBottomTextButton(onBackBtnClick = {
            navController.popBackStack()
        }, onNextBtnClick = {
            if (credentialsVM.emailLoginTextFieldState.value.isNotEmpty()
                && credentialsVM.passwordLoginTextFieldState.value.isNotEmpty()
            ) {
                navController.navigate(NavigationItem.PERSONAL_INFO.route)
            } else
                Toast.makeText(context,R.string.alert_empty_email_password, Toast.LENGTH_SHORT).show()
        })

        BackHandler(onBack = {
            // Navigate back to the previous screen
            navController.popBackStack()
        })
    }
}