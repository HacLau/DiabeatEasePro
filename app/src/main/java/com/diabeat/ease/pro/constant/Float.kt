package com.diabeat.ease.pro.constant

fun Float.formatTwo():String{
    return  if (this.toInt().toFloat() == this){
        this.toInt().formatTwo()
    }else if ((this * 10 ).toInt().toFloat() == this * 10){
        "${this}0"
    }else{
        "$this"
    }
}