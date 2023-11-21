package com.diabeat.ease.pro.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.NumberPicker
import com.diabeat.ease.pro.R

class NumberSelector : NumberPicker {
    constructor(context: Context?) : super(context){
        initSelector()
    }

    private fun initSelector() {
        descendantFocusability = FOCUS_BLOCK_DESCENDANTS
        wrapSelectorWheel = false

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            selectionDividerHeight = 0
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initSelector()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initSelector()
    }

    override fun addView(child: View) {
        super.addView(child)
        updateView(child)
    }

    override fun addView(child: View, params: ViewGroup.LayoutParams) {
        super.addView(child, params)
        updateView(child)
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        super.addView(child, index, params)
        updateView(child)
    }

    @SuppressLint("ResourceType")
    private fun updateView(view: View) {
        if (view is EditText) {
            view.setTextColor(resources.getColor(R.color.number_picker))
            view.textSize = 12f
        }
    }

}