package com.diabeat.ease.pro.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.diabeat.ease.pro.app
import kotlin.reflect.KProperty

object Shared {
    var defaultSelectTime by Preference("defaultSelectTime", "Default")
    var currentUnit by Preference("currentUnit", "mg/dl")
    var launchedStart by Preference("launchedStart", false)
    var launchedStep by Preference("launchedStep", false)
    var backgroundTime by Preference("backgroundTime", System.currentTimeMillis())
}

class Preference<T>(val name: String, val default: T) {
    private val prefs: SharedPreferences by lazy { app.getSharedPreferences(name, Context.MODE_PRIVATE) }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharedPreferences(name, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharedPreferences(name, value)
    }

    @SuppressLint("CommitPrefEdits")
    private fun putSharedPreferences(key: String, value: T) =
        with(prefs.edit()) {
            when (value) {
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                else -> throw IllegalArgumentException("Unsupported type.")
            }
        }.apply()


    @Suppress("UNCHECKED_CAST")
    private fun getSharedPreferences(key: String, defValue: T): T =
        when (defValue) {
            is Int -> prefs.getInt(key, defValue)
            is Long -> prefs.getLong(key, defValue)
            is Float -> prefs.getFloat(key, defValue)
            is Boolean -> prefs.getBoolean(key, defValue)
            is String -> prefs.getString(key, defValue)
            else -> throw IllegalArgumentException("Unsupported type.")
        } as T

}