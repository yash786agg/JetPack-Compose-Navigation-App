package com.android.onboarding.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CredentialsVM : ViewModel() {
    val emailTextFieldState = mutableStateOf("")
    val passwordTextFieldState = mutableStateOf("")

    /**
     * Returns an email text field value as it helps to survive
     * screen configuration change.
     * @param  emailTextFieldState  an email for credentials of String variable type
     * @return mutableState value of email
     */

    fun onEmailQueryChanged(emailTextFieldState: String) {
        this.emailTextFieldState.value = emailTextFieldState
    }

    /**
     * Returns an password text field value as it helps to survive
     * screen configuration change.
     * @param passwordTextFieldState an password for credentials of String variable type
     * @return mutableState value of password
     */

    fun onPasswordQueryChanged(passwordTextFieldState: String) {
        this.passwordTextFieldState.value = passwordTextFieldState
    }
}