package com.android.onboarding.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TermsOfServiceVM : ViewModel() {

    val checkedState = mutableStateOf(false)

    /**
     * Returns an State of the Checked Button state also survive the
     * screen configuration change.
     * @param checkedState is a Boolean variable type
     * @return mutableState value of Terms Of Service State
     */
    fun onCheckedStateChange(checkedState: Boolean) {
        this.checkedState.value = checkedState
    }

}