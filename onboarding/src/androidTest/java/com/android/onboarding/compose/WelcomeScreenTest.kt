package com.android.onboarding.compose

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            WelcomeScreen(navController = TestNavHostController(LocalContext.current))
        }
    }
    @Test
    fun welcomeScreenComposeTest() {
        // Finding and controlling the UI component
        val welcomeText = composeTestRule.onNodeWithText("Welcome")
        assertTrue(welcomeText.isDisplayed())

        // Interacting with the UI component
        composeTestRule.onNodeWithText("Get Started").assertHasClickAction()
    }
}