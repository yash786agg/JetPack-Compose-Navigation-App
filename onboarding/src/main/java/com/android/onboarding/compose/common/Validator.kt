package com.android.onboarding.compose.common

import android.util.Log

object Validator {
    fun validateEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}".toRegex()
        Log.e("Validator","Validator emailPattern: ${emailPattern.matches(email)}")
        return emailPattern.matches(email)
    }

    fun validateTelephoneNumber(telephoneNumber: String): Boolean {
        // Regular expression pattern for basic phone number validation
        val telephoneNumberPattern = Regex("^\\+(?:[0-9] ?){6,14}[0-9]$")
        return telephoneNumberPattern.matches(telephoneNumber)
    }
}