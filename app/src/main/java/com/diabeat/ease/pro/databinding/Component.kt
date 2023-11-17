package com.diabeat.ease.pro.databinding

import android.webkit.WebView
import androidx.databinding.BindingAdapter

object Component {
    @BindingAdapter("url")
    @JvmStatic
    fun WebView.setUrl(value:String){
        loadUrl(value)
    }
}