package com.diabeat.ease.pro.activity

import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.databinding.ActivityQaBinding
import com.diabeat.ease.pro.databinding.QA

class QaActivity : BaseActivity<ActivityQaBinding>(R.layout.activity_qa) {
    override fun initData() {
        binding.title = "Article"
        binding.activity = this
        intent.getParcelableExtra<QA>("qa")?.apply {
            binding.qa = this
        }

    }

}