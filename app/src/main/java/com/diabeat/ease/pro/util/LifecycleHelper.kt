package com.diabeat.ease.pro.util

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.diabeat.ease.pro.activity.StepActivity

class LifecycleHelper(private val application: Application) : Application.ActivityLifecycleCallbacks {
    private var running = 0
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {
        if (running == 0
            && System.currentTimeMillis() - Shared.backgroundTime > 5000
            && (activity is StepActivity).not()
        ) {
            application.startActivity(Intent(application, StepActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            })
        }
        running++

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {
        running--
        if (running == 0) {
            Shared.backgroundTime = System.currentTimeMillis()
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }
}