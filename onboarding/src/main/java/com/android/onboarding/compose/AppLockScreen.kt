package com.android.onboarding.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.navigation.NavigationItem
import com.android.onboarding.R
import com.android.onboarding.compose.common.DataStorePreferences.PIN
import com.android.onboarding.compose.common.DataStorePreferences.dataStore
import com.android.onboarding.compose.common.DataStorePreferences.getValueFlow
import com.android.onboarding.compose.common.PinInput
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun AppLockScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        var pinValue by rememberSaveable { mutableStateOf("") }
        Text(
            text = stringResource(id = R.string.text_unlock_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        PinInput(pinValue = pinValue, onPinValueChanged = { newValue ->
            pinValue = newValue
        })

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {
            if (pinValue.isNotEmpty()) {
                coroutineScope.launch {
                    val pin = dataStore?.getValueFlow(PIN, "")?.first() ?: ""
                    if (pin == pinValue)
                        navController.navigate(NavigationItem.SPLASH.route)
                    else
                        Toast.makeText(context, R.string.alert_invalid_pin, Toast.LENGTH_SHORT).show()
                }
            }else
                Toast.makeText(context, R.string.alert_empty_pin, Toast.LENGTH_SHORT).show()

        }) {
            Text(
                text = stringResource(id = R.string.text_unlock),
                fontSize = 19.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}