package com.android.onboarding.compose

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun PersonalInfoScreen(navController: NavHostController) {
    BackHandler(onBack = {
        // Navigate back to the previous screen
        navController.popBackStack()
    })
}