package com.android.onboarding.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PersonalInfoVM: ViewModel() {

    val firstNameTextFieldState = mutableStateOf("")
    val lastNameTextFieldState = mutableStateOf("")
    val telephoneNumberTextFieldState = mutableStateOf("")

    /**
     * Returns an firstName text field value as it helps to survive
     * screen configuration change.
     * @param  firstNameTextFieldState an firstName of String variable type
     * @return mutableState value of firstName
     */

    fun onFirstNameQueryChanged(firstNameTextFieldState: String) {
        this.firstNameTextFieldState.value = firstNameTextFieldState
    }

    /**
     * Returns an lastName text field value as it helps to survive
     * screen configuration change.
     * @param  lastNameTextFieldState an lastName of String variable type
     * @return mutableState value of lastName
     */

    fun onLastNameQueryChanged(lastNameTextFieldState: String) {
        this.lastNameTextFieldState.value = lastNameTextFieldState
    }

    /**
     * Returns an telephoneNumber text field value as it helps to survive
     * screen configuration change.
     * @param  telephoneNumberTextFieldState an telephoneNumber of String variable type
     * @return mutableState value of telephoneNumber
     */

    fun onTelephoneNumberQueryChanged(telephoneNumberTextFieldState: String) {
        this.telephoneNumberTextFieldState.value = telephoneNumberTextFieldState
    }
}