package com.diabeat.ease.pro.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.databinding.LayoutConditionPopBinding


class ConditionPop(
    private val context: Context
) : AlertDialog(context) {
    private lateinit var binding: LayoutConditionPopBinding
    lateinit var list: MutableList<String>
    var checkIndex: Int = -1
    var cancelClick: () -> Unit = {}
    var confirmClick: (String) -> Unit = { }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutConditionPopBinding.inflate(LayoutInflater.from(context))
        this.window?.setGravity(Gravity.BOTTOM)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.window?.setDimAmount(0.5f)
        initData()
        setContentView(binding.root)
    }

    private fun initData() {
        binding.cancelClick = View.OnClickListener {
            dismiss()
            cancelClick.invoke()
        }
        binding.confirmClick = View.OnClickListener {
            dismiss()
            confirmClick.invoke(list[checkIndex])
        }
        setFlowLayout()
    }

    private fun setFlowLayout() {
        binding.content.removeAllViews()
        for (i in list.indices) {
            (LayoutInflater.from(context).inflate(R.layout.layout_flow_item, binding.content, false) as CheckBox).apply {
                text = list[i]
                setOnClickListener {
                    if (checkIndex != -1) {
                        (binding.content.getChildAt(checkIndex) as CheckBox).isChecked = false
                    }
                    checkIndex = i
                    isChecked = true
                }
                binding.content.addView(this)
            }
        }
        if (list.isNotEmpty()) {
            if (checkIndex == -1) checkIndex = 0
            (binding.content.getChildAt(checkIndex) as CheckBox).isChecked = true
        }
    }
}