package com.diabeat.ease.pro

import android.app.Application
import com.diabeat.ease.pro.cloak.CloakHelper
import com.diabeat.ease.pro.util.LifecycleHelper

lateinit var app:Application
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        app = this
        registerActivityLifecycleCallbacks(LifecycleHelper(this))
        CloakHelper.requestCloakConfig()
    }
}