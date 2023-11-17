package com.diabeat.ease.pro.databinding

import androidx.annotation.DrawableRes
import androidx.databinding.BaseObservable
import com.diabeat.ease.pro.R

data class Step(
    var title:String = "",
    @DrawableRes
    var image:Int = R.mipmap.ic_launcher_round
):BaseObservable()

val listStep:MutableList<Step> = mutableListOf(
    Step("One-Tap Blood Sugar Recording\nEffortlessly track your blood sugar levels", R.mipmap.image_step_1),
    Step("Data Visualization & Analysis\nUnderstand trends in your body", R.mipmap.image_step_2),
    Step("Professional Data Management\nGet expert support for your blood sugar data", R.mipmap.image_step_3)
)
