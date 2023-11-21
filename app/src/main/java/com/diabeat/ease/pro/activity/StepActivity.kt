package com.diabeat.ease.pro.activity

import android.content.res.ColorStateList
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.diabeat.ease.pro.BuildConfig
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.adapter.StepAdapter
import com.diabeat.ease.pro.constant.dp2px
import com.diabeat.ease.pro.constant.px2dp
import com.diabeat.ease.pro.databinding.ActivityStepBinding
import com.diabeat.ease.pro.databinding.listStep
import com.diabeat.ease.pro.util.Shared
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class StepActivity : BaseActivity<ActivityStepBinding>(R.layout.activity_step) {
    private val next: MutableList<String> = mutableListOf(
        "Next", "Next", "Start record"
    )
    private val preWidth = 14
    private val nextWidth = 28
    override fun initData() {
        binding.activity = this
        if (Shared.launchedStart.not())
            initStartView()
        else
            initProgressView()
    }

    private fun initProgressView() {
        binding.currentStep = 1
        Timer().schedule(object : TimerTask() {
            override fun run() {
                if (binding.splashProgress.progress >= 100) {
                    if (isResume) {
                        cancel()
                        CoroutineScope(Dispatchers.Main).launch {
                            if (Shared.launchedStep) {
                                startMainActivity()
                            } else {
                                initStepView()
                            }
                        }

                    }
                } else {
                    binding.splashProgress.progress++
                }

            }
        }, 33, 33)
    }

    private fun initStartView() {
        binding.currentStep = 0
        binding.startPrivacy.movementMethod = LinkMovementMethod.getInstance()
        binding.startPrivacy.text = viewModel.spanText(onPrivacy = { title, url ->
            startWebActivity(title, url)
        }, onAgreement = { title, url ->
            startWebActivity(title, url)
        })
    }
    fun initStepView() {
        binding.currentStep = 2
        binding.vp.apply {
            adapter = StepAdapter(this@StepActivity, listStep)
            addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    setPagePro(position)
                    setButtonText(position)
                }

                override fun onPageScrollStateChanged(state: Int) {

                }

            })
        }
        setButtonText(0)
        setPagePro(0)
    }

    fun clickNext(){
        if(binding.vp.currentItem == 2){
            startMainActivity()
            Shared.launchedStep = true
        }else{
            binding.vp.currentItem += 1
        }
    }
    fun clickStart(){
        initProgressView()
        Shared.launchedStart = true
    }

    private fun setButtonText(position: Int) {
        binding.next = next[position]
    }

    private fun setPagePro(position: Int) {

        when (position) {
            0 -> {
//                binding.page1.layoutParams.width = nextWidth.dp2px(this)
//                binding.page2.layoutParams.width = preWidth.dp2px(this)
//                binding.page3.layoutParams.width = preWidth.dp2px(this)
                ColorStateList.valueOf(this.resources.getColor(R.color.bg_qa_item_icon_0)).let {
                    binding.page1.imageTintList = it
                }
                ColorStateList.valueOf(this.resources.getColor(R.color.bg_step)).let {
                    binding.page2.imageTintList = it
                    binding.page3.imageTintList = it
                }
            }

            1 -> {
//                binding.page1.layoutParams.width = preWidth.dp2px(this)
//                binding.page2.layoutParams.width = nextWidth.dp2px(this)
//                binding.page3.layoutParams.width = preWidth.dp2px(this)
                ColorStateList.valueOf(this.resources.getColor(R.color.bg_qa_item_icon_0)).let {
                    binding.page2.imageTintList = it
                }
                ColorStateList.valueOf(this.resources.getColor(R.color.bg_step)).let {
                    binding.page1.imageTintList = it
                    binding.page3.imageTintList = it
                }
            }

            2 -> {
//                binding.page1.layoutParams.width = preWidth.dp2px(this)
//                binding.page2.layoutParams.width = preWidth.dp2px(this)
//                binding.page3.layoutParams.width = nextWidth.dp2px(this)
                ColorStateList.valueOf(this.resources.getColor(R.color.bg_qa_item_icon_0)).let {
                    binding.page3.imageTintList = it
                }
                ColorStateList.valueOf(this.resources.getColor(R.color.bg_step)).let {
                    binding.page2.imageTintList = it
                    binding.page1.imageTintList = it
                }
            }
        }

        Log.e("page","width = ${binding.page1.layoutParams.width} width = ${binding.page2.layoutParams.width} position = $position")


    }

}