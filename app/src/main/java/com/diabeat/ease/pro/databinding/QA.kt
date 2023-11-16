package com.diabeat.ease.pro.databinding

import androidx.annotation.DrawableRes
import androidx.databinding.BaseObservable

data class QA(
    @DrawableRes
    var icon:Int,
    var title:String,
    var content:String,
    var from:String
) : BaseObservable()