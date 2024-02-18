package com.android.navigation_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.navigation.NavigationItem
import com.android.navigation_app.MainActivity
import com.android.navigation_app.ui.splash.SplashScreen
import com.android.onboarding.compose.CredentialsScreen
import com.android.onboarding.compose.PersonalInfoScreen
import com.android.onboarding.compose.TermsOfServiceScreen
import com.android.onboarding.compose.WelcomeScreen
import com.android.onboarding.vm.CredentialsVM
import com.android.onboarding.vm.TermsOfServiceVM

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationItem.SPLASH.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.SPLASH.route) {
            SplashScreen(navController = navController)
        }

        composable(NavigationItem.WELCOME.route) {
            WelcomeScreen(navController = navController)
        }

        composable(NavigationItem.TERMS_OF_SERVICE.route) {
            val termsOfServiceVM = viewModel<TermsOfServiceVM>()
            TermsOfServiceScreen(navController = navController,termsOfServiceVM = termsOfServiceVM)
        }

        composable(NavigationItem.CREDENTIALS.route) {
            val credentialsVM = viewModel<CredentialsVM>()
            CredentialsScreen(navController = navController,credentialsVM = credentialsVM)
        }

        composable(NavigationItem.PERSONAL_INFO.route) {
            PersonalInfoScreen(navController = navController)
        }
    }
}