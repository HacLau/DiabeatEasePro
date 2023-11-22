package com.diabeat.ease.pro.activity

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.adapter.SugarAdapter
import com.diabeat.ease.pro.databinding.ActivityHistoryBinding
import com.diabeat.ease.pro.db.DbHelper
import com.diabeat.ease.pro.ui.ItemBottomDecoration

class HistoryActivity : BaseActivity<ActivityHistoryBinding>(R.layout.activity_history) {
    private var sugarAdapter = SugarAdapter(this) {
        startEditActivity(it) {
            setResult(Activity.RESULT_OK)
            resetSugarData()
        }
    }
    override fun initData() {
        binding.title = "History Record"
        binding.activity = this@HistoryActivity
        binding.sugarRv.apply {
            addItemDecoration(ItemBottomDecoration(context, 12))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = sugarAdapter
        }
        resetSugarData()
    }

    private fun resetSugarData() {
        DbHelper.query().let {
            sugarAdapter.list = it
        }

    }
}