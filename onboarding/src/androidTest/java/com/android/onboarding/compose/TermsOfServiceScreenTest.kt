package com.android.onboarding.compose

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.onboarding.vm.TermsOfServiceVM
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TermsOfServiceScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val termsOfServiceVM = TermsOfServiceVM()

    @Test
    fun termsOfServiceScreenComposeTest() {
        composeTestRule.setContent {
            TermsOfServiceScreen(
                navController = NavHostController(LocalContext.current),
                termsOfServiceVM = termsOfServiceVM
            )
        }

        // Finding and controlling the UI component
        val termsOfServiceText = composeTestRule.onNodeWithText("Terms of Service")
        assertTrue(termsOfServiceText.isDisplayed())

        val descriptionTermsOfService = composeTestRule.onNodeWithTag("DescriptionTermsOfService")
        assertFalse(descriptionTermsOfService.isNotDisplayed())

        val checkbox = composeTestRule.onNodeWithTag("Checkbox")
        checkbox.performClick()
    }
}