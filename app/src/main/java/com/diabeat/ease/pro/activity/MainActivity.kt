package com.diabeat.ease.pro.activity

import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.adapter.MainAdapter
import com.diabeat.ease.pro.adapter.MeAdapter
import com.diabeat.ease.pro.adapter.QaAdapter
import com.diabeat.ease.pro.adapter.SugarAdapter
import com.diabeat.ease.pro.constant.formatTwo
import com.diabeat.ease.pro.databinding.ActivityMainBinding
import com.diabeat.ease.pro.databinding.LayoutHomeBinding
import com.diabeat.ease.pro.databinding.LayoutMeBinding
import com.diabeat.ease.pro.databinding.LayoutQaBinding
import com.diabeat.ease.pro.databinding.LayoutSugarBinding
import com.diabeat.ease.pro.databinding.RG
import com.diabeat.ease.pro.databinding.meList
import com.diabeat.ease.pro.db.DbHelper
import com.diabeat.ease.pro.ui.ItemBottomDecoration
import com.diabeat.ease.pro.ui.ItemLRBottomDecoration

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private var sugarAdapter = SugarAdapter(this, 1) {
        startEditActivity(it) {
            resetSugarData()
        }
    }
    private lateinit var homeBinding: LayoutHomeBinding
    private lateinit var sugarBinding: LayoutSugarBinding
    private lateinit var qaBinding: LayoutQaBinding
    private lateinit var meBinding: LayoutMeBinding

    override fun initData() {
        binding.activity = this
        homeBinding = initHomeBinding()
        sugarBinding = initSugarBinding()
        qaBinding = initQaBinding()
        meBinding = initMeBinding()
        binding.vp.apply {
            adapter = MainAdapter(this@MainActivity, mutableListOf(homeBinding.root, sugarBinding.root, qaBinding.root, meBinding.root))
            addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    binding.currentIndex = position
                }

                override fun onPageScrollStateChanged(state: Int) {

                }

            })
        }
        binding.rg = RG()
        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            binding.vp.currentItem = when (id) {
                binding.rbHome.id -> 0
                binding.rbSugar.id -> 1
                binding.rbQa.id -> 2
                binding.rbMe.id -> 3
                else -> 0
            }

        }
        resetSugarData()
    }

    private fun initHomeBinding(): LayoutHomeBinding = LayoutHomeBinding.inflate(LayoutInflater.from(this@MainActivity)).apply {
        activity = this@MainActivity
        this.homeRv.apply {
            addItemDecoration(ItemLRBottomDecoration(context, 6))
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
            adapter = QaAdapter(context, type = 2) {
                startQaActivity(it)
            }
        }
    }

    private fun initSugarBinding(): LayoutSugarBinding = LayoutSugarBinding.inflate(LayoutInflater.from(this@MainActivity)).apply {
        this.sugarRv.apply {
            addItemDecoration(ItemBottomDecoration(context, 12))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = sugarAdapter
        }
    }

    private fun initQaBinding(): LayoutQaBinding = LayoutQaBinding.inflate(LayoutInflater.from(this@MainActivity)).apply {
        this.qaRv.apply {
            addItemDecoration(ItemLRBottomDecoration(context, 6))
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
            adapter = QaAdapter(context) {
                startQaActivity(it)
            }
        }
    }

    private fun initMeBinding(): LayoutMeBinding = LayoutMeBinding.inflate(LayoutInflater.from(this@MainActivity)).apply {
        this.meRv.apply {
            addItemDecoration(ItemBottomDecoration(context, 12))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MeAdapter(context) { me, position ->
                when (position) {
                    0 -> startWebActivity(me.title, "https://blog.csdn.net/qq_38110571/article/details/123005448")
                    1 -> startWebActivity(me.title, "https://www.jianshu.com/p/e8cf690ee8cc")
                    2 -> sharedApp()
                    3 -> upgradeApp()
                }
            }
        }
    }


    fun startNewRecordActivity() {
        startNewActivity {
            binding.vp.currentItem = 1
            resetSugarData()
        }
    }

    private fun resetSugarData() {
        DbHelper.query().let {
            sugarAdapter.list = it
            sugarBinding.dataRecent.text = if (it.isNotEmpty()) "${it[0].data}" else "0.0"
        }


        DbHelper.query().forEach {
            Log.e("MainActivity", it.toString())
        }
    }

}