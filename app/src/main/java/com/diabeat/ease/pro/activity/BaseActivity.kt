package com.diabeat.ease.pro.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.diabeat.ease.pro.AppModel
import com.diabeat.ease.pro.BuildConfig
import com.diabeat.ease.pro.databinding.QA
import com.diabeat.ease.pro.databinding.Sugar

abstract class BaseActivity<VB : ViewDataBinding>(@LayoutRes var redId: Int) : AppCompatActivity() {
    val binding: VB by lazy { DataBindingUtil.setContentView(this, redId) }
    val viewModel: AppModel by lazy { ViewModelProvider(this)[AppModel::class.java] }
    private var onResultSuccess: () -> Unit = {}

    private var forResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            onResultSuccess.invoke()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    abstract fun initData()

    fun startNewActivity(onResult: () -> Unit) {
        onResultSuccess = onResult
        forResult.launch(Intent(this, NewActivity::class.java))
    }

    fun startEditActivity(sugar: Sugar,onResult: () -> Unit) {
        onResultSuccess = onResult
        forResult.launch(Intent(this, NewActivity::class.java).apply {
            putExtra("type", 2)
            putExtra("sugar", sugar)
        })
    }

    fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun startQaActivity(qa: QA) {
        startActivity(Intent(this, QaActivity::class.java).apply {
            putExtra("qa", qa)
        })
    }

    fun startWebActivity(title: String, url: String) {
        startActivity(Intent(this, WebActivity::class.java).apply {
            putExtra("title", title)
            putExtra("url", url)
        })
    }

    fun sharedApp() {
        kotlin.runCatching {
            startActivity(Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
            })
        }
    }

    fun upgradeApp() {

    }
}