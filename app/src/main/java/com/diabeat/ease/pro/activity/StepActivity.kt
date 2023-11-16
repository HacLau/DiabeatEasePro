package com.diabeat.ease.pro.activity

import android.content.res.ColorStateList
import android.util.Log
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.constant.dp2px
import com.diabeat.ease.pro.constant.px2dp
import com.diabeat.ease.pro.databinding.ActivityStepBinding

class StepActivity : BaseActivity<ActivityStepBinding>(R.layout.activity_step) {
    private val next: MutableList<String> = mutableListOf(
        "Next", "Next", "Start record"
    )

    override fun initData() {
        binding.activity = this
        binding.vp.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                setPagePro(position)
                setButtonText(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
        setButtonText(0)
        setPagePro(0)
    }


    private fun setButtonText(position: Int) {
        binding.next = next[position]
    }

    private fun setPagePro(position: Int) {

        when (position) {
            0 -> {
                binding.page1.layoutParams.width = 56.dp2px(this)
                binding.page1.layoutParams.width = 28.dp2px(this)
                binding.page1.layoutParams.width = 28.dp2px(this)
                Log.e("page","width = ${binding.page1.layoutParams.width}")
                ColorStateList.valueOf(this.getColor(R.color.bg_qa_item_icon_0)).let {
                    binding.page1.imageTintList = it
                }
                ColorStateList.valueOf(this.getColor(R.color.bg_step)).let {
                    binding.page2.imageTintList = it
                    binding.page3.imageTintList = it
                }
            }

            1 -> {
                binding.page2.layoutParams.width = 28.dp2px(this)
                binding.page2.layoutParams.width = 56.dp2px(this)
                binding.page2.layoutParams.width = 28.dp2px(this)
                ColorStateList.valueOf(this.getColor(R.color.bg_qa_item_icon_0)).let {
                    binding.page2.imageTintList = it
                }
                ColorStateList.valueOf(this.getColor(R.color.bg_step)).let {
                    binding.page1.imageTintList = it
                    binding.page3.imageTintList = it
                }
            }

            2 -> {
                binding.page1.layoutParams.width = 28.dp2px(this)
                binding.page2.layoutParams.width = 28.dp2px(this)
                binding.page3.layoutParams.width = 56.dp2px(this)
                ColorStateList.valueOf(this.getColor(R.color.bg_qa_item_icon_0)).let {
                    binding.page3.imageTintList = it
                }
                ColorStateList.valueOf(this.getColor(R.color.bg_step)).let {
                    binding.page2.imageTintList = it
                    binding.page1.imageTintList = it
                }
            }
        }


    }

}