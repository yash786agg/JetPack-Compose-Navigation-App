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
fun TermsOfServiceScreen(navController: NavHostController, termsOfServiceVM: TermsOfServiceVM) {
    // Retrieve the current state of the checkbox from the ViewModel
    val checkedState = termsOfServiceVM.checkedState.value
    // Retrieve the current context
    val context = LocalContext.current

    // Column layout to arrange child composable vertically
    Column(
        modifier = Modifier
            .fillMaxSize() // Occupy the entire available space
            .padding(30.dp) // Apply padding around the column content
    ) {
        // Title text for the terms of service screen
        Text(
            text = stringResource(id = R.string.text_terms_of_service),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            // Determine text color based on system theme
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            modifier = Modifier.padding(bottom = 16.dp) // Apply bottom padding to the title
        )

        // Description text for the terms of service
        Text(
            text = stringResource(id = R.string.text_terms_of_service_description),
            fontSize = 16.sp,
            // Determine text color based on system theme
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )

        // Spacer to create vertical space between elements
        Spacer(modifier = Modifier.height(24.dp))

        // Row layout to arrange child composables horizontally
        Row(
            verticalAlignment = Alignment.CenterVertically // Align children vertically in the center
        ) {
            // Checkbox for agreeing to terms and conditions
            Checkbox(
                checked = checkedState, // Set the checked state of the checkbox
                onCheckedChange = { isChecked -> termsOfServiceVM.onCheckedStateChange(checkedState = isChecked) }, // Handle checkbox state change
                modifier = Modifier.padding(end = 8.dp) // Apply end padding to the checkbox
            )
            // Text label for the checkbox
            Text(
                text = stringResource(id = R.string.text_agree_to_terms_and_condition),
                fontSize = 14.sp,
                // Determine text color based on system theme
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        }

        // Spacer to push content to the bottom of the screen
        Spacer(modifier = Modifier.weight(1f))

        // Bottom button for navigation
        onBoardingBottomTextButton(onBackBtnClick = {
            // Navigate back when back button is clicked
            navController.popBackStack()
        }, onNextBtnClick = {
            // Navigate to the next screen if checkbox is checked, otherwise show a toast
            if (checkedState) navController.navigate(NavigationItem.CREDENTIALS.route)
            else Toast.makeText(context, R.string.checkbox_alert, Toast.LENGTH_SHORT).show()
        })

        // Handle back button press
        BackHandler(onBack = {
            // Navigate back to the previous screen
            navController.popBackStack()
        })
    }
}