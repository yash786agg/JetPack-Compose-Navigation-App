package com.android.navigation_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.navigation.NavigationItem
import com.android.navigation_app.ui.common.Constants.PIN_ARGUMENT
import com.android.navigation_app.ui.common.Constants.PIN_ARGUMENT_KEY
import com.android.navigation_app.ui.common.Constants.PIN_DEFAULT_VALUE
import com.android.navigation_app.ui.splash.SplashScreen
import com.android.onboarding.compose.AppLockScreen
import com.android.onboarding.compose.ConfirmPinScreen
import com.android.onboarding.compose.CredentialsScreen
import com.android.onboarding.compose.HomeScreen
import com.android.onboarding.compose.NewPinScreen
import com.android.onboarding.compose.PersonalInfoScreen
import com.android.onboarding.compose.TermsOfServiceScreen
import com.android.onboarding.compose.WelcomeScreen
import com.android.onboarding.vm.CredentialsVM
import com.android.onboarding.vm.PersonalInfoVM
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
            TermsOfServiceScreen(navController = navController, termsOfServiceVM = termsOfServiceVM)
        }

        composable(NavigationItem.CREDENTIALS.route) {
            val credentialsVM = viewModel<CredentialsVM>()
            CredentialsScreen(navController = navController, credentialsVM = credentialsVM)
        }

        composable(NavigationItem.PERSONAL_INFO.route) {
            val personalInfoVM = viewModel<PersonalInfoVM>()
            PersonalInfoScreen(navController = navController, personalInfoVM = personalInfoVM)
        }

        composable(NavigationItem.NEW_PIN.route) {
            NewPinScreen(navController = navController)
        }

        composable(NavigationItem.CONFIRM_PIN.route + PIN_ARGUMENT_KEY, arguments = listOf(
            navArgument(PIN_ARGUMENT) {
                type = NavType.StringType
                defaultValue = PIN_DEFAULT_VALUE
                nullable = true
            }
        )) { navEntry ->
            navEntry.arguments?.getString(PIN_ARGUMENT)
                ?.let { pin ->
                    ConfirmPinScreen(pin = pin, navController = navController)
                }
        }

        composable(NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(NavigationItem.APP_LOCK.route) {
            AppLockScreen(navController = navController)
        }
    }
}