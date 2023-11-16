package com.diabeat.ease.pro.constant

import android.content.Context

fun Int.formatTwoString(): String {
    return if (this.toString().length == 1) {
        "0$this"
    } else {
        this.toString()
    }
}

fun Int.dp2px(context: Context): Int = (context.resources.displayMetrics.density * this).toInt()
fun Int.px2dp(context: Context): Int = (this.toFloat() / context.resources.displayMetrics.density ).toInt()