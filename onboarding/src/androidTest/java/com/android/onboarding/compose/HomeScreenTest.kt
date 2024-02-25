package com.android.onboarding.compose

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.onboarding.compose.common.DataStorePreferences.getValueFlow
import com.android.onboarding.compose.common.DataStorePreferences.setValue
import com.android.onboarding.compose.common.TestConstants.DATASTORE_EMAIL_TEST_KEY
import com.android.onboarding.compose.common.TestConstants.DATASTORE_NAME_TEST_KEY
import com.android.onboarding.compose.common.TestConstants.DATASTORE_TELEPHONE_TEST_KEY
import com.android.onboarding.compose.common.TestConstants.DATA_STORE_PREFERENCES_EMAIL_TEST_KEY
import com.android.onboarding.compose.common.TestConstants.DATA_STORE_PREFERENCES_NAME_TEST_KEY
import com.android.onboarding.compose.common.TestConstants.DATA_STORE_PREFERENCES_TELEPHONE_TEST_KEY
import com.android.onboarding.compose.common.TestConstants.INVALID_TEST_EMAIL
import com.android.onboarding.compose.common.TestConstants.INVALID_TEST_NAME
import com.android.onboarding.compose.common.TestConstants.INVALID_TEST_TELEPHONENUMBER
import com.android.onboarding.compose.common.TestConstants.TEST_EMAIL
import com.android.onboarding.compose.common.TestConstants.TEST_NAME
import com.android.onboarding.compose.common.TestConstants.TEST_TELEPHONENUMBER
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    private val testContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private val TEST_NAME_KEY = stringPreferencesKey(DATASTORE_NAME_TEST_KEY)

    private val TEST_EMAIL_KEY = stringPreferencesKey(DATASTORE_EMAIL_TEST_KEY)

    private val TEST_TELEPHONE_KEY = stringPreferencesKey(DATASTORE_TELEPHONE_TEST_KEY)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HomeScreen(navController = NavHostController(LocalContext.current))
        }
    }

    @Test
    fun displaysTextComposableTest() = runTest {

        val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
            produceFile = {
                testContext.preferencesDataStoreFile(
                    DATA_STORE_PREFERENCES_NAME_TEST_KEY
                )
            })

        dataStore.setValue(TEST_NAME_KEY, TEST_NAME)

        val nameText = composeTestRule.onNodeWithTag("name")
        nameText.assertExists()

        val name = dataStore.getValueFlow(TEST_NAME_KEY, "").first()
        delay(1000)

        assertEquals(name, TEST_NAME)
        assertNotEquals(name, INVALID_TEST_NAME)

        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    @Test
    fun displaysEmailComposableTest() = runTest {

        val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
            produceFile = {
                testContext.preferencesDataStoreFile(
                    DATA_STORE_PREFERENCES_EMAIL_TEST_KEY
                )
            })

        dataStore.setValue(TEST_EMAIL_KEY, TEST_EMAIL)

        val emailText = composeTestRule.onNodeWithTag("email")
        emailText.assertExists()

        val email = dataStore.getValueFlow(TEST_EMAIL_KEY, "").first()
        delay(1000)

        assertEquals(email, TEST_EMAIL)
        assertNotEquals(email, INVALID_TEST_EMAIL)

        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    @Test
    fun displaysTelephoneNumberComposableTest() = runTest {

        val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
            produceFile = {
                testContext.preferencesDataStoreFile(
                    DATA_STORE_PREFERENCES_TELEPHONE_TEST_KEY
                )
            })

        dataStore.setValue(TEST_TELEPHONE_KEY, TEST_TELEPHONENUMBER)

        val telephoneNumberText = composeTestRule.onNodeWithTag("telephoneNumber")
        telephoneNumberText.assertExists()

        val telephoneNumber = dataStore.getValueFlow(TEST_TELEPHONE_KEY, "").first()
        delay(1000)

        assertEquals(telephoneNumber, TEST_TELEPHONENUMBER)
        assertNotEquals(telephoneNumber, INVALID_TEST_TELEPHONENUMBER)

        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    @Test
    fun nextButtonTest() {
        val signOutBtn = composeTestRule.onNodeWithText("Sign Out")
        signOutBtn.assertHasClickAction()
        signOutBtn.assertIsDisplayed()
    }
}