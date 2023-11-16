package com.diabeat.ease.pro.databinding

import android.view.View.OnClickListener
import androidx.annotation.DrawableRes
import androidx.databinding.BaseObservable
import com.diabeat.ease.pro.R

data class Sugar(
    var level: Int = 0,
    var time: Long,
    var data: Float,
    @DrawableRes
    var topBg: Int = R.color.bg_sugar_item_top_0,
    @DrawableRes
    var bottomBg: Int = R.color.bg_sugar_item_0,
    @DrawableRes
    var timeColor: Int = R.color.text_sugar_item_title_0,
    var icon: Int = R.mipmap.ic_launcher,
    var titleList:MutableList<String> = mutableListOf(
        "Low","Normal","Pre-diabetes","Diabetes"
    ),
    var onItemClick:OnClickListener = OnClickListener {  }
) : BaseObservable()