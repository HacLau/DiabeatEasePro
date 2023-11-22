package com.diabeat.ease.pro.cloak

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import com.diabeat.ease.pro.BuildConfig
import com.diabeat.ease.pro.app
import com.diabeat.ease.pro.constant.encode
import com.diabeat.ease.pro.util.IpHelper
import com.diabeat.ease.pro.util.Shared
import org.json.JSONObject
import java.util.Locale
import java.util.UUID

object CloakHelper {
    val distinct_id = "will"
    val client_ts = "barnet"
    val device_model = "foam"
    val bundle_id = "comic"
    val os_version = "terror"
    val os = "minus"
    val android_id = "wooster"
    val app_version = "salary"
    val ip = "triplett"
    val operator = "barlow"

    var loadCount = 0
    fun requestCloakConfig() {
        HttpHelper.sendRequestGet(BuildConfig.clockUrl, getConfig(),
            resultSuccess = {
                loadCount = 0
                Shared.cloakState = it
            }, resultFailed = { _, _ ->
                if (++loadCount < 20) {
                    requestCloakConfig()
                }
            })
    }

    private fun getConfig(): String {
        return StringBuilder().apply {
            mutableMapOf(
                distinct_id to getAndroidId().encode(),
                client_ts to System.currentTimeMillis().toString().encode(),
                device_model to Build.MODEL.encode(),
                bundle_id to BuildConfig.APPLICATION_ID.encode(),
                os_version to Build.VERSION.RELEASE.encode(),
                os to "kiwanis".encode(),
                android_id to getAndroidId().encode(),
                app_version to BuildConfig.VERSION_NAME.encode()
            ).forEach {
                this.append("&${it.key}=${it.value}")
            }
        }.toString().substring(1)
    }


    @SuppressLint("HardwareIds")
    private fun getAndroidId(): String {
        if (Shared.androidId.isBlank()) {
            Shared.androidId = Settings.Secure.getString(app.contentResolver, Settings.Secure.ANDROID_ID)
            if (Shared.androidId.isBlank()) {
                Shared.androidId = UUID.randomUUID().toString()
            }
        }
        return Shared.androidId
    }

}
