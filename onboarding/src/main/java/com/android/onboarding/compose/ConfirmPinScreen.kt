package com.android.onboarding.compose

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.navigation.NavigationItem
import com.android.onboarding.R
import com.android.onboarding.compose.common.DataStorePreferences.ON_BOARDING
import com.android.onboarding.compose.common.DataStorePreferences.PIN
import com.android.onboarding.compose.common.DataStorePreferences.dataStore
import com.android.onboarding.compose.common.DataStorePreferences.setValue
import com.android.onboarding.compose.common.PinInput
import com.android.onboarding.compose.common.OnBoardingBottomTextButton
import kotlinx.coroutines.launch

@Composable
fun ConfirmPinScreen(pin: String, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        var confirmPinValue by rememberSaveable { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.text_confirm_pin),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            PinInput(pinValue = confirmPinValue, onPinValueChanged = { newValue ->
                confirmPinValue = newValue
            })
        }

        // Spacer to push the next element (onBoardingBottomTextButton) to the bottom
        Spacer(modifier = Modifier.weight(1f))

        // Bottom button
        OnBoardingBottomTextButton(
            onBackBtnClick = {
                navController.popBackStack()
            },
            onNextBtnClick = {
                if (confirmPinValue.isNotEmpty()) {
                    if (pin == confirmPinValue) {
                        coroutineScope.launch {
                            dataStore?.setValue(ON_BOARDING, true)
                            dataStore?.setValue(PIN,confirmPinValue)
                        }
                        navController.navigate(NavigationItem.Home.route)
                    } else
                        Toast.makeText(context, R.string.alert_pins_are_not_same, Toast.LENGTH_SHORT).show()
                } else
                    Toast.makeText(context, R.string.alert_empty_pin, Toast.LENGTH_SHORT).show()
            }
        )

        BackHandler(onBack = {
            // Navigate back to the previous screen
            navController.popBackStack()
        })
    }
}