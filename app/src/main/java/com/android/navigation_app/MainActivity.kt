package com.android.navigation_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.android.navigation.NavigationItem
import com.android.navigation_app.ui.navigation.AppNavHost
import com.android.navigation_app.ui.theme.NavigationAppTheme
import com.android.onboarding.compose.common.DataStorePreferences.PIN
import com.android.onboarding.compose.common.DataStorePreferences.dataStore
import com.android.onboarding.compose.common.DataStorePreferences.getValueFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var pin by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(Unit) {
                pin = dataStore?.getValueFlow(PIN, "")?.first() ?: ""
            }
            NavigationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    if(pin.isNotEmpty())
                        AppNavHost(navController = navController,startDestination = NavigationItem.APP_LOCK.route)
                    else
                        AppNavHost(navController = navController,startDestination = NavigationItem.SPLASH.route)
                }
            }
        }
    }
}
