package com.diabeat.ease.pro.activity

import android.util.Log
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.databinding.ActivityQaBinding
import com.diabeat.ease.pro.databinding.QA

class QaActivity : BaseActivity<ActivityQaBinding>(R.layout.activity_qa) {
    override fun initData() {
        binding.title = ""
        binding.activity = this
        binding.qa = intent.getParcelableExtra("qa")

    }

}