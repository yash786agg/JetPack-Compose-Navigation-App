package com.android.onboarding.compose

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.onboarding.vm.TermsOfServiceVM
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TermsOfServiceScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val termsOfServiceVM = TermsOfServiceVM()

    private lateinit var context: Context

    @Before
    fun setUp() {
        composeTestRule.setContent {
            context = LocalContext.current
            TermsOfServiceScreen(
                navController = TestNavHostController(LocalContext.current),
                termsOfServiceVM = termsOfServiceVM
            )
        }
    }

    @Test
    fun termsOfServiceScreenComposeTest() {
        // Finding and controlling the UI component
        val termsOfServiceText = composeTestRule.onNodeWithText("Terms of Service")
        assertTrue(termsOfServiceText.isDisplayed())

        val descriptionTermsOfService = composeTestRule.onNodeWithTag("DescriptionTermsOfService")
        assertFalse(descriptionTermsOfService.isNotDisplayed())

        val checkbox = composeTestRule.onNodeWithTag("Checkbox")
        checkbox.performClick()
    }

    @Test
    fun nextButtonTest() {
        composeTestRule.onNodeWithText("Next").assertHasClickAction()
    }
}