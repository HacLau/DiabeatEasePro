package com.diabeat.ease.pro.activity

import android.content.res.ColorStateList
import android.util.Log
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.adapter.StepAdapter
import com.diabeat.ease.pro.constant.dp2px
import com.diabeat.ease.pro.constant.px2dp
import com.diabeat.ease.pro.databinding.ActivityStepBinding
import com.diabeat.ease.pro.databinding.listStep

class StepActivity : BaseActivity<ActivityStepBinding>(R.layout.activity_step) {
    private val next: MutableList<String> = mutableListOf(
        "Next", "Next", "Start record"
    )
    private val preWidth = 14
    private val nextWidth = 28
    override fun initData() {
        binding.activity = this
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
        }else{
            binding.vp.currentItem += 1
        }
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