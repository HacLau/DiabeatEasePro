package com.diabeat.ease.pro.constant

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.net.URLEncoder

fun String.log(){
    Log.e("StringKt",this)
}
fun String.toast(context: Context){
    Toast.makeText(context,this,Toast.LENGTH_SHORT).show()
}

fun String.encode():String{
    return URLEncoder.encode(this, "UTF-8")
}

fun String.logE(){
    Log.e("StringKt",this)
}