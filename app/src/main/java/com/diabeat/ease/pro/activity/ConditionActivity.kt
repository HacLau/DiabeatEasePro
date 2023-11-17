package com.diabeat.ease.pro.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.adapter.ConditionAdapter
import com.diabeat.ease.pro.databinding.ActivityConditionBinding
import com.diabeat.ease.pro.ui.ItemBottomDecoration

class ConditionActivity : BaseActivity<ActivityConditionBinding>(R.layout.activity_condition) {
    override fun initData() {
        binding.activity = this
        binding.conditionRv.apply {
            addItemDecoration(ItemBottomDecoration(context, 12))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ConditionAdapter(this@ConditionActivity){

            }
        }
    }
}