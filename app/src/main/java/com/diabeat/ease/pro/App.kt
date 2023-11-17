package com.diabeat.ease.pro

import android.app.Application
lateinit var app:Application
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        app = this
    }
}