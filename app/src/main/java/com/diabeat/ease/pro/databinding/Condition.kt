package com.diabeat.ease.pro.databinding

import androidx.databinding.BaseObservable
import com.diabeat.ease.pro.util.Shared

data class Condition(
    var title: String = "",
    var content: String = "",
    var unit: String = Shared.currentUnit,
    var dl: List<Float> = dlList,
    var mol: List<Float> = molList,
    var titleList: MutableList<String> = mutableListOf(
        "Low", "Normal", "Pre-diabetes", "Diabetes"
    ),
    var dataList: MutableList<String> = when (unit) {
        unitList[0] -> mutableListOf("<${dl[0]}", "${dl[0]}-${dl[1]}", "${dl[1]}-${dl[2]}", ">=${dl[2]}")
        else -> mutableListOf("<${mol[0]}", "${mol[0]}-${mol[1]}", "${mol[1]}-${mol[2]}", ">=${mol[2]}")
    }

) : BaseObservable()

val conditionList: List<Condition> by lazy {
    mutableListOf(
        Condition(title = "Default", content = "For all unspecified conditions"),
        Condition(title = "Before exercise"),
        Condition(title = "Before a meal"),
        Condition(title = "Fasting"),
        Condition(title = "After a meal(1h)", dl = mutableListOf(72.0f, 140.0f, 153.0f), mol = mutableListOf(4.0f, 7.8f, 8.5f)),
        Condition(title = "After a meal(2h)", dl = mutableListOf(72.0f, 85.0f, 126.0f), mol = mutableListOf(4.0f, 4.7f, 7.0f)),
        Condition(title = "After exercise"),
        Condition(title = "Asleep")
    )
}

val unitList: List<String> by lazy {
    mutableListOf(
        "mg/dl",
        "mmol/l"
    )
}

val dlList: List<Float> by lazy {
    mutableListOf(
        72.0f, 99.0f, 126.0f
    )
}

val molList: List<Float> by lazy {
    mutableListOf(
        4.0f, 5.5f, 7.0f
    )
}