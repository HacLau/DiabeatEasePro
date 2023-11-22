package com.diabeat.ease.pro.constant

import java.util.Calendar

fun Long.formatTimeMain(): String {
    return Calendar.getInstance().let {
        it.timeInMillis = this
        "${(it.get(Calendar.MONTH) + 1)}.${it.get(Calendar.DATE)}"
    }
}

fun Long.formatTimeTwo(): String {
    return Calendar.getInstance().let {
        it.timeInMillis = this
        "${(it.get(Calendar.MONTH) + 1).formatTwoString()}.${it.get(Calendar.DATE).formatTwoString()}"
    }
}

fun Long.formatTimeItem(): String {
    return Calendar.getInstance().let {
        it.timeInMillis = this
        "${it.get(Calendar.YEAR)}/${(it.get(Calendar.MONTH) + 1).formatTwoString()}/${it.get(Calendar.DATE).formatTwoString()} ${it.get(Calendar.HOUR_OF_DAY).formatTwoString()}:${it.get(Calendar.MINUTE).formatTwoString()}"
    }
}

fun Long.formatTimeNew(): MutableList<String> {
    return mutableListOf<String>().apply {
        Calendar.getInstance().let {
            it.timeInMillis = this@formatTimeNew
            add("${it.get(Calendar.YEAR)}")
            add((it.get(Calendar.MONTH) + 1).formatTwoString())
            add(it.get(Calendar.DATE).formatTwoString())
            add(it.get(Calendar.HOUR_OF_DAY).formatTwoString())
            add(it.get(Calendar.MINUTE).formatTwoString())
        }
    }
}

fun Long.getPre3Days():Long{
    return Calendar.getInstance().let {
        it.set(it.get(Calendar.YEAR),it.get(Calendar.MONTH),it.get(Calendar.DAY_OF_MONTH - 2),0,0,0)
        it.timeInMillis
    }

}

fun Int.getLastDaysMills():Long{
    return Calendar.getInstance().let {
        it.set(Calendar.DATE, it.get(Calendar.DATE) - this)
        it.set(Calendar.HOUR_OF_DAY, 0)
        it.set(Calendar.MINUTE, 0)
        it.set(Calendar.SECOND, 0)
        it.timeInMillis
    }
}


fun Long.getCurrentDateArrayByMill(): IntArray {
    return Calendar.getInstance().let {
        it.timeInMillis = this
        intArrayOf(
            it.get(Calendar.YEAR),
            it.get(Calendar.MONTH) + 1,
            it.get(Calendar.DATE),
            it.get(Calendar.HOUR_OF_DAY),
            it.get(Calendar.MINUTE)
        )
    }
}

fun IntArray.getCurrentDateMillByArray(): Long {
    return Calendar.getInstance().let {
        it.set(Calendar.YEAR, this[0])
        it.set(Calendar.MONTH, this[1] - 1)
        it.set(Calendar.DATE, this[2])
        it.set(Calendar.HOUR_OF_DAY, this[3])
        it.set(Calendar.MINUTE, this[4])
        it.timeInMillis
    }
}

fun Long.getLastYearToDay():Long{
    return Calendar.getInstance().let {
        it.timeInMillis = this
        it.add(Calendar.YEAR,-1)
        it.timeInMillis
    }
}

