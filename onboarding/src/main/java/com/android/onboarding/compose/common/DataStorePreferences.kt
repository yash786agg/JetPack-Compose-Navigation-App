package com.android.onboarding.compose.common

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.android.onboarding.compose.common.DataStoreConstants.DATASTORE_EMAIL_KEY
import com.android.onboarding.compose.common.DataStoreConstants.DATASTORE_NAME_KEY
import com.android.onboarding.compose.common.DataStoreConstants.DATASTORE_ON_BOARDING_KEY
import com.android.onboarding.compose.common.DataStoreConstants.DATASTORE_PIN_KEY
import com.android.onboarding.compose.common.DataStoreConstants.DATASTORE_TELEPHONE_KEY
import com.android.onboarding.compose.common.DataStoreConstants.DATA_STORE_PREFERENCES_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

object DataStorePreferences {
    private var application: Application? = null
    var dataStore: DataStore<Preferences>? = null

    fun init(application: Application) {
        this.application = application
        dataStore = application.dataStorePreferences
    }

    private val Context.dataStorePreferences by preferencesDataStore(DATA_STORE_PREFERENCES_KEY)

    val ON_BOARDING = booleanPreferencesKey(DATASTORE_ON_BOARDING_KEY)

    val NAME = stringPreferencesKey(DATASTORE_NAME_KEY)

    val EMAIL = stringPreferencesKey(DATASTORE_EMAIL_KEY)

    val TELEPHONE = stringPreferencesKey(DATASTORE_TELEPHONE_KEY)

    val PIN = stringPreferencesKey(DATASTORE_PIN_KEY)

    fun <T> DataStore<Preferences>.getValueFlow(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> {
        return this.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                }
            }.map { preferences ->
                preferences[key] ?: defaultValue
            }
    }

    suspend fun <T> DataStore<Preferences>.setValue(key: Preferences.Key<T>, value: T) {
        this.edit { preferences ->
            preferences[key] = value
        }
    }
}