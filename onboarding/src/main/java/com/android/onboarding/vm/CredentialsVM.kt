package com.android.onboarding.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CredentialsVM : ViewModel() {
    val emailLoginTextFieldState = mutableStateOf("")
    val passwordLoginTextFieldState = mutableStateOf("")

    /**
     * Returns an email text field value as it helps to survive
     * screen configuration change.
     * @param  emailLoginTextFieldState  an email for credentials of String variable type
     * @return mutableState value of email
     */

    fun onEmailQueryChanged(emailLoginTextFieldState: String) {
        this.emailLoginTextFieldState.value = emailLoginTextFieldState
    }

    /**
     * Returns an password text field value as it helps to survive
     * screen configuration change.
     * @param passwordLoginTextFieldState an password for credentials of String variable type
     * @return mutableState value of password
     */

    fun onPasswordQueryChanged(passwordLoginTextFieldState: String) {
        this.passwordLoginTextFieldState.value = passwordLoginTextFieldState
    }
}