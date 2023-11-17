package com.diabeat.ease.pro.databinding

import android.os.Parcelable
import android.view.View.OnClickListener
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.databinding.BaseObservable
import com.diabeat.ease.pro.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sugar(
    var id:Int = 0,
    var level: Int = 0,
    var data: Float = 0f,
    var time: Long = System.currentTimeMillis(),
    var kind: String = ""
) : BaseObservable(), Parcelable


var titleList: MutableList<String> = mutableListOf(
    "Low", "Normal", "Pre-diabetes", "Diabetes"
)
var desList: MutableList<String> = mutableListOf(
    "Low", "Normal", "Pre-diabetes", "Diabetes"
)
var iconList: MutableList<Int> = mutableListOf(
    R.mipmap.ic_level_0,
    R.mipmap.ic_level_1,
    R.mipmap.ic_level_2,
    R.mipmap.ic_level_3
)

var topBgList: MutableList<Int> = mutableListOf(
    R.color.bg_sugar_item_top_0,
    R.color.bg_sugar_item_top_1,
    R.color.bg_sugar_item_top_2,
    R.color.bg_sugar_item_top_3
)

var bottomBgList: MutableList<Int> = mutableListOf(
    R.color.bg_sugar_item_0,
    R.color.bg_sugar_item_1,
    R.color.bg_sugar_item_2,
    R.color.bg_sugar_item_3
)

var titleColorList: MutableList<Int> = mutableListOf(
    R.color.text_sugar_item_title_0,
    R.color.text_sugar_item_title_1,
    R.color.text_sugar_item_title_2,
    R.color.text_sugar_item_title_3
)


