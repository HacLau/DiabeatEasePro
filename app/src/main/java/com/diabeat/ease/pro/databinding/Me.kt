package com.diabeat.ease.pro.databinding

import androidx.databinding.BaseObservable

data class Me (
    var title:String = ""
):BaseObservable()


val meList:List<Me> by lazy {
    mutableListOf(
        Me("Feedback"),
        Me("Privacy Policy"),
        Me("Share app")
    )
}