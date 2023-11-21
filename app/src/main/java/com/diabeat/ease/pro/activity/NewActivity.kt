package com.diabeat.ease.pro.activity

import android.app.Activity
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.constant.formatTimeItem
import com.diabeat.ease.pro.constant.formatTimeNew
import com.diabeat.ease.pro.constant.formatTwo
import com.diabeat.ease.pro.constant.log
import com.diabeat.ease.pro.databinding.ActivityNewBinding
import com.diabeat.ease.pro.databinding.Sugar
import com.diabeat.ease.pro.databinding.conditionList
import com.diabeat.ease.pro.databinding.desLevelList
import com.diabeat.ease.pro.databinding.desList
import com.diabeat.ease.pro.databinding.dlList
import com.diabeat.ease.pro.databinding.iconList
import com.diabeat.ease.pro.databinding.molList
import com.diabeat.ease.pro.databinding.titleList
import com.diabeat.ease.pro.databinding.unitList
import com.diabeat.ease.pro.db.DbHelper
import com.diabeat.ease.pro.ui.ConditionPop
import com.diabeat.ease.pro.ui.ConfirmDialog
import com.diabeat.ease.pro.ui.DateTimeDialog
import com.diabeat.ease.pro.util.Shared
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

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
        sugar = intent.getParcelableExtra("sugar") ?: Sugar(unit = Shared.currentUnit)
        binding.sugar = sugar
        binding.iconData = iconList
        binding.editUnit.text = sugar.unit
        binding.editData.apply {
            inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_CLASS_NUMBER
            setOnEditorActionListener { _, _, _ ->
                clearFocus()
                isCursorVisible = false
                checkInputData()
                false
            }
        }

        getLevelInSugar()
    }

    private fun checkInputData() {
        "${Shared.currentUnit}   ${binding.editData.text}".log()
        if (binding.editData.text.toString().isNotBlank()) {
            val data = binding.editData.text.toString().toFloat()
            if (Shared.currentUnit == unitList[0] && (data < 18.0f || data > 630.0f)) {
                binding.editNotice.text = "Please input right number range 18-630mg/dl"
                binding.editNotice.visibility = View.VISIBLE
                sugar.data = 85.5f
            } else if (Shared.currentUnit == unitList[1] && (data < 1.0f || data > 35.0f)) {
                binding.editNotice.text = "Please input right number range 1.0-35.0 mmol/l"
                binding.editNotice.visibility = View.VISIBLE
                sugar.data = 4.75f
            } else {
                binding.editNotice.visibility = View.GONE
                sugar.data = binding.editData.text.toString().toFloat()
            }
        } else {
            if (Shared.currentUnit == unitList[0]) {
                binding.editNotice.text = "Please input right number range 18-630mg/dl"
                binding.editNotice.visibility = View.VISIBLE
                sugar.data = 85.5f
            } else if (Shared.currentUnit == unitList[1]) {
                binding.editNotice.text = "Please input right number range 1.0-35.0 mmol/l"
                binding.editNotice.visibility = View.VISIBLE
                sugar.data = 4.75f
            }
        }
        getLevelInSugar()
    }

    private fun getLevelInSugar() {
        sugar.level = when (Shared.currentUnit) {
            unitList[0] -> {
                when (sugar.data) {
                    in 0f..<dlList[0] -> 0
                    in dlList[0]..<dlList[1] -> 1
                    in dlList[1]..<dlList[2] -> 2
                    else -> 3
                }
            }

            unitList[1] -> {
                when (sugar.data) {
                    in 0f..<molList[0] -> 0
                    in molList[0]..<molList[1] -> 1
                    in molList[1]..<molList[2] -> 2
                    else -> 3
                }
            }

            else -> 0
        }
        binding.sugar = sugar
        conditionList.filter {
            it.title == sugar.kind
        }.let {
            "kind = ${sugar.kind}  conditionList.size = ${conditionList.size}  it = ${it.size}".log()
            if (it.isNotEmpty()) {
                binding.titleData = it[0].titleList
                binding.desData = it[0].desLevelList()
            }
        }

    }

    fun saveData() {
        sugar.unit = Shared.currentUnit
        sugar.data = binding.editData.text.toString().toFloat()
        when (type) {
            1 -> {
                val list = DbHelper.queryByTime(sugar.showTime)
                if (list.isEmpty()) {
                    DbHelper.insert(sugar)
                    setResult(Activity.RESULT_OK)
                    finish()
                } else {
                    ConfirmDialog(this@NewActivity, clickConfirm = {
                        DbHelper.update(sugar.apply {
                            id = list[0].id
                        })
                        setResult(Activity.RESULT_OK)
                        finish()
                    }).show()

                }
            }

            else -> {
                DbHelper.update(sugar)
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    fun changeUnit() {
        when (Shared.currentUnit) {
            unitList[0] -> {
                Shared.currentUnit = unitList[1]
            }
            unitList[1] -> {
                Shared.currentUnit = unitList[0]
            }
        }
        binding.editUnit.text = Shared.currentUnit
        checkInputData()
        setResult(Activity.RESULT_OK)
    }

    fun selectTime() {
        ConditionPop(this).apply {
            list = viewModel.conditionTime
            checkIndex = viewModel.conditionTime.indexOf(sugar.kind)
            cancelClick = {

            }
            confirmClick = {
                sugar.kind = it
                binding.sugar = sugar
            }
        }.show()
    }

    fun visibleNotice() {
        binding.editNotice.visibility = View.GONE
    }

    fun changeTime() {
        DateTimeDialog(this, sugar.time, confirm = {
            sugar.time = it
            sugar.showTime = it.formatTimeItem()
            binding.sugar = sugar
            getLevelInSugar()
        }).show()
    }


}