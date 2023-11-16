package com.diabeat.ease.pro.databinding

import android.view.View
import android.view.View.OnClickListener
import androidx.databinding.BaseObservable

data class Me (
    var title:String = "",
    var onItemCLick:OnClickListener = OnClickListener { }
):BaseObservable()