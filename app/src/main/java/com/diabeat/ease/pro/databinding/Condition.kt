package com.diabeat.ease.pro.databinding

import androidx.databinding.BaseObservable

data class Condition(
    var title: String,
    var content: String,
    var titleList:MutableList<String> = mutableListOf(
        "Low","Normal","Pre-diabetes","Diabetes"
    ),
    var dataList:MutableList<String> = mutableListOf(
        "<72.0","72.0-99.0","99.0-126.0",">126.0"
    )
) : BaseObservable()

