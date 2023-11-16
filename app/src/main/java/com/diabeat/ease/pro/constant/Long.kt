package com.diabeat.ease.pro.constant

import java.util.Calendar

fun Long.formatTimeMain(): String {
    return Calendar.getInstance().let {
        it.timeInMillis = this
        "${(it.get(Calendar.MONTH) + 1)}.${it.get(Calendar.DATE)}"
    }
}

fun Long.formatTimeItem(): String {
    return Calendar.getInstance().let {
        it.timeInMillis = this
        "${it.get(Calendar.YEAR)}/${(it.get(Calendar.MONTH) + 1).formatTwoString()}/${it.get(Calendar.DATE).formatTwoString()} ${it.get(Calendar.HOUR_OF_DAY).formatTwoString()}:${it.get(Calendar.MINUTE).formatTwoString()}"
    }
}

