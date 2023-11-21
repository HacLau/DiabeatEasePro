package com.diabeat.ease.pro.databinding

import android.os.Parcelable
import androidx.databinding.BaseObservable
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.util.Shared
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sugar(
    var id: Int = 0,
    var level: Int = 0,
    var data: Float = 0f,
    var time: Long = System.currentTimeMillis(),
    var kind: String = conditionList[0].title,
    var unit: String = Shared.currentUnit
) : BaseObservable(), Parcelable


val titleList: MutableList<String> by lazy {
    mutableListOf(
        "Low", "Normal", "Pre-diabetes", "Diabetes"
    )
}
val desList: MutableList<String> by lazy {
    mutableListOf(
        "Low", "Normal", "Pre-diabetes", "Diabetes"
    )
}
val iconList: MutableList<Int> by lazy {
    mutableListOf(
        R.mipmap.ic_level_0,
        R.mipmap.ic_level_1,
        R.mipmap.ic_level_2,
        R.mipmap.ic_level_3
    )
}

val topBgList: MutableList<Int> by lazy {
    mutableListOf(
        R.color.bg_sugar_item_top_0,
        R.color.bg_sugar_item_top_1,
        R.color.bg_sugar_item_top_2,
        R.color.bg_sugar_item_top_3
    )
}

val bottomBgList: MutableList<Int> by lazy {
    mutableListOf(
        R.color.bg_sugar_item_0,
        R.color.bg_sugar_item_1,
        R.color.bg_sugar_item_2,
        R.color.bg_sugar_item_3
    )
}

val titleColorList: MutableList<Int> by lazy {
    mutableListOf(
        R.color.text_sugar_item_title_0,
        R.color.text_sugar_item_title_1,
        R.color.text_sugar_item_title_2,
        R.color.text_sugar_item_title_3
    )
}


