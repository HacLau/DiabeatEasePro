package com.diabeat.ease.pro.databinding

import android.view.View
import android.view.View.OnClickListener
import androidx.databinding.BaseObservable

data class Me (
    var title:String = ""
):BaseObservable()


val meList:List<Me> = mutableListOf(
    Me("Feedback"),
    Me("Privacy Policy"),
    Me("Share app"),
    Me("Upgrade")
)