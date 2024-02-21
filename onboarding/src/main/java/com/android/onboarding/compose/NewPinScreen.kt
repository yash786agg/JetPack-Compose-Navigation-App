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
import com.android.onboarding.compose.common.PinInput
import com.android.onboarding.compose.common.onBoardingBottomTextButton

@Composable
fun NewPinScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        val context = LocalContext.current
        var pinValue by rememberSaveable { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.text_create_new_pin),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            PinInput(pinValue = pinValue, onPinValueChanged = { newValue ->
                pinValue = newValue
            })
        }

        // Spacer to push the next element (onBoardingBottomTextButton) to the bottom
        Spacer(modifier = Modifier.weight(1f))

        // Bottom button
        onBoardingBottomTextButton(onBackBtnClick = {
            navController.popBackStack()
        }, onNextBtnClick = {
            if (pinValue.isNotEmpty()) if (pinValue.length == 4) navController.navigate(
                NavigationItem.CONFIRM_PIN.moveToConfirmPinWithArgs(
                    pinValue
                )
            )
            else Toast.makeText(context, R.string.alert_invalid_pin, Toast.LENGTH_SHORT).show()
            else Toast.makeText(context, R.string.alert_empty_pin, Toast.LENGTH_SHORT).show()
        })

        BackHandler(onBack = {
            // Navigate back to the previous screen
            navController.popBackStack()
        })
    }
}