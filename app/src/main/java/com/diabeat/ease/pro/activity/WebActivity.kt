package com.diabeat.ease.pro.activity

import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.databinding.ActivityWebBinding
import com.diabeat.ease.pro.databinding.Web

class WebActivity : BaseActivity<ActivityWebBinding>(R.layout.activity_web) {
    override fun initData() {

        binding.web = Web(intent.getStringExtra("title")!!, intent.getStringExtra("url")!!)
        binding.activity = this
    }
}