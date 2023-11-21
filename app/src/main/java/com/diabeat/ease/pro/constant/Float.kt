package com.diabeat.ease.pro.constant

import com.diabeat.ease.pro.databinding.unitList
import com.diabeat.ease.pro.util.Shared

fun Float.transData():Float{
    return when (Shared.currentUnit) {
        unitList[0] -> this * 18.0f
        else -> this
    }

}