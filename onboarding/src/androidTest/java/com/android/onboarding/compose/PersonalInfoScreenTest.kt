package com.android.onboarding.compose

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.onboarding.compose.common.TestConstants.FIRST_NAME_INPUT
import com.android.onboarding.compose.common.TestConstants.INVALID_TELEPHONE_TEST_INPUT
import com.android.onboarding.compose.common.TestConstants.LAST_NAME_INPUT
import com.android.onboarding.compose.common.TestConstants.TELEPHONE_TEST_INPUT
import com.android.onboarding.compose.common.Validator.validateTelephoneNumber
import com.android.onboarding.vm.PersonalInfoVM
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PersonalInfoScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val personalInfoVM: PersonalInfoVM = PersonalInfoVM()



    @Before
    fun setUp() {
        composeTestRule.setContent {
            PersonalInfoScreen(
                navController = NavHostController(LocalContext.current),
                personalInfoVM = personalInfoVM
            )
        }
    }

    @Test
    fun firstNameTextFieldTest() {
        val firstNameTextField = composeTestRule.onNodeWithTag("firstNameTextField")
        firstNameTextField.performTextInput(FIRST_NAME_INPUT)
        firstNameTextField.assertTextContains(FIRST_NAME_INPUT)

        assertNotEquals(FIRST_NAME_INPUT,"")
    }

    @Test
    fun lastNameTextFieldTest() {
        val lastNameTextField = composeTestRule.onNodeWithTag("lastNameTextField")
        lastNameTextField.performTextInput(LAST_NAME_INPUT)
        lastNameTextField.assertTextContains(LAST_NAME_INPUT)

        assertNotEquals(LAST_NAME_INPUT,"")
    }

    @Test
    fun telephoneNumberTextFieldTest() {
        val lastNameTextField = composeTestRule.onNodeWithTag("telephoneNumberTextField")
        lastNameTextField.performTextInput(TELEPHONE_TEST_INPUT)
        lastNameTextField.assertTextContains(TELEPHONE_TEST_INPUT)

        assertTrue(validateTelephoneNumber(TELEPHONE_TEST_INPUT))

        assertFalse(validateTelephoneNumber(INVALID_TELEPHONE_TEST_INPUT))
        lastNameTextField.performTextClearance()
        lastNameTextField.performTextInput(INVALID_TELEPHONE_TEST_INPUT)

        runBlocking { delay(1000) }

        val telephoneNumberSupportingTextField = composeTestRule.onNodeWithContentDescription("telephoneNumberSupportingText")
        telephoneNumberSupportingTextField.assertExists("InValid Telephone For Example:- +1234567")
        assertNotEquals("InValid Telephone For Example:- +1234567","")
    }

    @Test
    fun nextButtonTest() {
        composeTestRule.onNodeWithText("Next").assertHasClickAction()
    }
}