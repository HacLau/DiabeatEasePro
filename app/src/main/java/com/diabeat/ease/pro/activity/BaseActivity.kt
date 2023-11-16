package com.diabeat.ease.pro.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.diabeat.ease.pro.AppModel

abstract class BaseActivity<VB : ViewDataBinding>(@LayoutRes var redId: Int) : AppCompatActivity() {
    val binding: VB by lazy { DataBindingUtil.setContentView(this, redId) }
    val viewModel: AppModel by lazy { ViewModelProvider(this)[AppModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    abstract fun initData()

    fun startNewActivity(){

    }

    fun startMainActivity(){

    }
}