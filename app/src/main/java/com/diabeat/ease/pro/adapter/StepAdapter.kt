package com.diabeat.ease.pro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.diabeat.ease.pro.databinding.LayoutStepItemBinding
import com.diabeat.ease.pro.databinding.LayoutSugarItemBinding
import com.diabeat.ease.pro.databinding.Step

class StepAdapter(
    private val context: Context,
    private val list: MutableList<Step>
) : PagerAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return LayoutStepItemBinding.inflate(LayoutInflater.from(context)).apply {
            step = list[position]
            container.addView(root)
        }.root
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}