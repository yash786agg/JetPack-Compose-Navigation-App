package com.android.navigation

enum class Screen {
    SPLASH,
    WELCOME,
    HOME,
    TERMS_OF_SERVICE,
    CREDENTIALS,
    PERSONAL_INFO
}
sealed class NavigationItem(val route: String) {
    object SPLASH: NavigationItem(Screen.SPLASH.name)
    object WELCOME: NavigationItem(Screen.WELCOME.name)
    object TERMS_OF_SERVICE: NavigationItem(Screen.TERMS_OF_SERVICE.name)
    object CREDENTIALS: NavigationItem(Screen.CREDENTIALS.name)
    object PERSONAL_INFO: NavigationItem(Screen.PERSONAL_INFO.name)
    object Home: NavigationItem(Screen.HOME.name)
}

