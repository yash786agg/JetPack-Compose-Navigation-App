package com.android.onboarding.compose

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.navigation.NavigationItem
import com.android.onboarding.R
import com.android.onboarding.compose.common.onBoardingBottomTextButton
import com.android.onboarding.vm.TermsOfServiceVM

@Composable
fun TermsOfServiceScreen(navController: NavHostController,termsOfServiceVM: TermsOfServiceVM) {
    val checkedState = termsOfServiceVM.checkedState.value
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(id = R.string.text_terms_of_service),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.text_terms_of_service_description),
            fontSize = 16.sp,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = { isChecked -> termsOfServiceVM.onCheckedStateChange(checkedState = isChecked) },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.text_agree_to_terms_and_condition),
                fontSize = 14.sp,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        onBoardingBottomTextButton(onBackBtnClick = {
            navController.popBackStack()
        }, onNextBtnClick = {
            if(checkedState)
                navController.navigate(NavigationItem.CREDENTIALS.route)
            else
                Toast.makeText(context,R.string.checkbox_alert,Toast.LENGTH_SHORT).show()
        })

        BackHandler(onBack = {
            // Navigate back to the previous screen
            navController.popBackStack()
        })
    }
}