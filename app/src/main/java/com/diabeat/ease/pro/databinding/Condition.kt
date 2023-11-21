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
        "Low", "Normal", "Pre ~ diabetes", "Diabetes"
    )

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

fun Condition.desLevelList():MutableList<String> = when (Shared.currentUnit) {
    unitList[0]  -> mutableListOf("<${this.dl[0]}${Shared.currentUnit}", "${this.dl[0]}${Shared.currentUnit} ~ ${this.dl[1]}${Shared.currentUnit}", "${this.dl[1]}${Shared.currentUnit} ~ ${this.dl[2]}${Shared.currentUnit}", ">=${this.dl[2]}${Shared.currentUnit}")
    else  -> mutableListOf("<${this.mol[0]}${Shared.currentUnit}", "${this.mol[0]}${Shared.currentUnit} ~ ${this.mol[1]}${Shared.currentUnit}", "${this.mol[1]}${Shared.currentUnit} ~ ${this.mol[2]}${Shared.currentUnit}", ">=${this.mol[2]}${Shared.currentUnit}")
}