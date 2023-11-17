package com.diabeat.ease.pro.databinding

import androidx.databinding.BaseObservable
import com.diabeat.ease.pro.constant.formatTimeMain

data class RG(
    var naviTitle:MutableList<String> = mutableListOf<String>().apply {
        add("Home")
        add("Blood Sugar")
        add("Q&A")
        add("Me")
        add("")
    },
    var titleList: MutableList<String> = mutableListOf<String>().apply {
        add(System.currentTimeMillis().formatTimeMain())
        add("Blood Sugar")
        add("Q&A")
        add("Me")
    }
): BaseObservable()