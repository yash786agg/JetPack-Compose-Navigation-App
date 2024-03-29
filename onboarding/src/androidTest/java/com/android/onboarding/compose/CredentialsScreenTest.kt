package com.android.onboarding.compose

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.onboarding.compose.common.TestConstants.EMAIL_TEST_INPUT
import com.android.onboarding.compose.common.TestConstants.INVALID_EMAIL_TEST_INPUT
import com.android.onboarding.compose.common.Validator.validateEmail
import com.android.onboarding.vm.CredentialsVM
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CredentialsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val credentialsVM = CredentialsVM()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CredentialsScreen(
                navController = TestNavHostController(LocalContext.current),
                credentialsVM = credentialsVM
            )
        }
    }

    @Test
    fun emailTextFieldTest() {

        val emailTextField = composeTestRule.onNodeWithTag("emailTextField")
        emailTextField.performTextInput(EMAIL_TEST_INPUT)
        emailTextField.assertTextContains(EMAIL_TEST_INPUT)

        assertTrue(validateEmail(EMAIL_TEST_INPUT))

        assertFalse(validateEmail(INVALID_EMAIL_TEST_INPUT))
    }

    @Test
    fun passwordTextFieldTest() {
        val passwordTextField = composeTestRule.onNodeWithTag("passwordTextField")
        passwordTextField.performTextInput("123")

        val passwordIconButton = composeTestRule.onNodeWithContentDescription("passwordIconButton")
        passwordIconButton.performClick()
        passwordIconButton.assertHasClickAction()

        passwordTextField.assertTextContains("123")

        assertEquals("123","123")
    }

    @Test
    fun nextButtonTest() {
        composeTestRule.onNodeWithText("Next").assertHasClickAction()
    }
}