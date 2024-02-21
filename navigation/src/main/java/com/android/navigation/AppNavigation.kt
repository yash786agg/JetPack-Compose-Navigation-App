package com.android.navigation


enum class Screen {
    SPLASH,
    WELCOME,
    HOME,
    TERMS_OF_SERVICE,
    CREDENTIALS,
    PERSONAL_INFO,
    NEW_PIN,
    CONFIRM_PIN,
    APP_LOCK
}
sealed class NavigationItem(val route: String) {
    object SPLASH: NavigationItem(Screen.SPLASH.name)
    object WELCOME: NavigationItem(Screen.WELCOME.name)
    object TERMS_OF_SERVICE: NavigationItem(Screen.TERMS_OF_SERVICE.name)
    object CREDENTIALS: NavigationItem(Screen.CREDENTIALS.name)
    object PERSONAL_INFO: NavigationItem(Screen.PERSONAL_INFO.name)
    object NEW_PIN: NavigationItem(Screen.NEW_PIN.name)
    object CONFIRM_PIN: NavigationItem(Screen.CONFIRM_PIN.name)
    object Home: NavigationItem(Screen.HOME.name)

    object APP_LOCK: NavigationItem(Screen.APP_LOCK.name)

    fun moveToConfirmPinWithArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}

