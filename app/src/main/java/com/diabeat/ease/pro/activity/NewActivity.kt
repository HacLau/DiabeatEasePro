package com.diabeat.ease.pro.activity

import android.app.Activity
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.Spanned
import android.text.TextWatcher
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.constant.log
import com.diabeat.ease.pro.databinding.ActivityNewBinding
import com.diabeat.ease.pro.databinding.Sugar
import com.diabeat.ease.pro.databinding.desList
import com.diabeat.ease.pro.databinding.iconList
import com.diabeat.ease.pro.databinding.titleList
import com.diabeat.ease.pro.db.DbHelper

class NewActivity : BaseActivity<ActivityNewBinding>(R.layout.activity_new) {
    private lateinit var sugar: Sugar
    private var type: Int = 1
    override fun initData() {
        binding.activity = this
        type = intent.getIntExtra("type", 1)
        binding.title = when (type) {
            1 -> "New Record"
            else -> "EditRecord"
        }
        sugar = intent.getParcelableExtra("sugar") ?: Sugar()
        binding.sugar = sugar
        binding.iconData = iconList
        binding.titleData = titleList
        binding.desData = desList
        binding.editData.apply {
            inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_CLASS_NUMBER
            filters = arrayOf(
                object :InputFilter{
                    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence {
                        source?.let {
                            if (it == "." && dest.toString().isEmpty()) {
                                return "0."
                            }
                        }
                        if (dest.toString().contains(".") && dest.toString().substring(dest.toString().indexOf(".")).length >= 3){
                            return ""
                        }
                        return source.toString()
                    }


                }
            )
        }
    }

    fun saveData(){
        sugar.data = binding.editData.text.toString().toFloat()
        val list = DbHelper.queryByTime(sugar.time)
        if (list.isEmpty()){
            when (type) {
                1 -> DbHelper.insert(sugar)
                else -> DbHelper.update(sugar)
            }
        }else{
            DbHelper.update(sugar.apply {
                id = list[0].id
            })
        }
        Log.e("NewActivity",sugar.toString())
        setResult(Activity.RESULT_OK)
        finish()
    }

    fun changeData(){

    }


}