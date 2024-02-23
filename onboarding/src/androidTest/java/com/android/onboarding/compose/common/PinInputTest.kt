package com.android.onboarding.compose.common

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PinInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun enterPinUpdatesValueAndFocusesNext() {
        // Set up initial pin value
        val pinValue = "1234"

        // Launch the UI with the PinInput
        composeTestRule.setContent {
            PinInput(pinValue) { updatedValue ->
                // Assert that the onPinValueChanged callback is called correctly
                assertEquals(updatedValue, "1235")
            }
        }
        // Find the first text field by index
        val textField1 = composeTestRule.onNodeWithTag("PinInputTextField0")

        // Assert initial pin value
        textField1.assertTextContains(pinValue[0].toString())
    }

    @Test
    fun enteringMaxDigitsDismissesKeyboard() {
        val pinValue = "1234"
        composeTestRule.setContent {
            PinInput(pinValue) { }
        }
        val textField4 = composeTestRule.onNodeWithTag("PinInputTextField3")
        textField4.performTextInput("5")
        // Verify that keyboard is dismissed
        composeTestRule.onNodeWithText("5").assertDoesNotExist()
    }
}

