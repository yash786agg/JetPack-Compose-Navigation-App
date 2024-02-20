package com.android.navigation_app

import android.app.Application
import com.android.onboarding.compose.common.DataStorePreferences

class NavigationApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DataStorePreferences.init(this)
    }
}