package com.diabeat.ease.pro.constant

import android.content.Context
import android.util.Log
import android.widget.Toast

fun String.log(){
    Log.e("StringKt",this)
}
fun String.toast(context: Context){
    Toast.makeText(context,this,Toast.LENGTH_SHORT).show()
}