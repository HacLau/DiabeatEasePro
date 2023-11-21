package com.diabeat.ease.pro.model

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.util.Base64
import android.view.View
import androidx.lifecycle.ViewModel
import com.diabeat.ease.pro.BuildConfig
import com.diabeat.ease.pro.app
import com.diabeat.ease.pro.databinding.QA
import com.diabeat.ease.pro.databinding.QAEntity
import com.diabeat.ease.pro.databinding.RG
import com.diabeat.ease.pro.databinding.conditionList
import com.diabeat.ease.pro.util.Shared
import com.google.gson.Gson
import java.nio.charset.Charset

class AppModel : ViewModel() {
    fun spanText(onPrivacy: (String, String) -> Unit, onAgreement: (String, String) -> Unit): CharSequence? {
        val text = "Read and agree to the Privacy Policy and Terms of Service"
        val key1 = "Privacy Policy"
        val key2 = "Terms of Service"
        return SpannableStringBuilder(text).apply {
            setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    onPrivacy.invoke(key1, BuildConfig.privacy)
                }

            }, text.indexOf(key1), text.indexOf(key1) + key1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    onAgreement.invoke(key2, BuildConfig.policy)
                }
            }, text.indexOf(key2), text.indexOf(key2) + key2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    val conditionTime = mutableListOf<String>().apply {
        conditionList.forEach {
            add(it.title)
        }
    }

}