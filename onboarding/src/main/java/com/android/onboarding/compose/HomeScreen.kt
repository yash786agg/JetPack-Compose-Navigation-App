package com.android.onboarding.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.navigation.NavigationItem
import com.android.onboarding.R
import com.android.onboarding.compose.common.DataStorePreferences.EMAIL
import com.android.onboarding.compose.common.DataStorePreferences.NAME
import com.android.onboarding.compose.common.DataStorePreferences.ON_BOARDING
import com.android.onboarding.compose.common.DataStorePreferences.PIN
import com.android.onboarding.compose.common.DataStorePreferences.TELEPHONE
import com.android.onboarding.compose.common.DataStorePreferences.dataStore
import com.android.onboarding.compose.common.DataStorePreferences.getValueFlow
import com.android.onboarding.compose.common.DataStorePreferences.setValue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutineScope = rememberCoroutineScope()
        var name by rememberSaveable { mutableStateOf("") }
        var email by rememberSaveable { mutableStateOf("") }
        var telephone by rememberSaveable { mutableStateOf("") }

        LaunchedEffect(Unit) {
            name = dataStore?.getValueFlow(NAME, "")?.first() ?: ""
            email = dataStore?.getValueFlow(EMAIL, "")?.first() ?: ""
            telephone = dataStore?.getValueFlow(TELEPHONE, "")?.first() ?: ""
        }

        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_android),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(stringResource(id = R.string.test_tag_name)),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = "${stringResource(id = R.string.text_hi)} $name"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(stringResource(id = R.string.test_tag_email)),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = "${stringResource(id = R.string.text_email)} $email"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .testTag(stringResource(id = R.string.test_tag_telephoneNumber)),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = "${stringResource(id = R.string.text_telephone)} $telephone"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            coroutineScope.launch {
                dataStore?.setValue(ON_BOARDING, false)
                dataStore?.setValue(PIN, "")
            }
            navController.navigate(NavigationItem.WELCOME.route)
        }) {
            Text(
                text = stringResource(id = R.string.text_sign_out),
                fontSize = 19.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}