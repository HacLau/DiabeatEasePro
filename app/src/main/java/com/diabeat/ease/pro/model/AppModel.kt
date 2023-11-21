package com.diabeat.ease.pro.model

import android.util.Base64
import androidx.lifecycle.ViewModel
import com.diabeat.ease.pro.app
import com.diabeat.ease.pro.databinding.QA
import com.diabeat.ease.pro.databinding.QAEntity
import com.diabeat.ease.pro.databinding.RG
import com.diabeat.ease.pro.databinding.conditionList
import com.diabeat.ease.pro.util.Shared
import com.google.gson.Gson
import java.nio.charset.Charset

class AppModel : ViewModel() {
    val conditionTime = mutableListOf<String>().apply {
        conditionList.forEach {
            add(it.title)
        }
    }

}