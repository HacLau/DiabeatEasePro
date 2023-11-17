package com.diabeat.ease.pro.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diabeat.ease.pro.databinding.LayoutSugarItemBinding
import com.diabeat.ease.pro.databinding.QA
import com.diabeat.ease.pro.databinding.Sugar
import com.diabeat.ease.pro.databinding.bottomBgList
import com.diabeat.ease.pro.databinding.iconList
import com.diabeat.ease.pro.databinding.qaList
import com.diabeat.ease.pro.databinding.titleColorList
import com.diabeat.ease.pro.databinding.titleList
import com.diabeat.ease.pro.databinding.topBgList

class SugarAdapter(
    private val context: Context,
    private var type: Int = 1,
    private val onItemClickListener: (Sugar) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<Sugar> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SugarItemViewHolder(LayoutSugarItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return if (type == 1) list.size else {
            if (list.size > 3) 3 else list.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SugarItemViewHolder) {
            holder.binding.apply {
                list[position].let {entity->
                    sugar = entity
                    itemClick = View.OnClickListener { onItemClickListener.invoke(entity) }
                    ColorStateList.valueOf(context.resources.getColor(topBgList[entity.level])).let {color->
                        topBg.imageTintList = color
                    }
                    ColorStateList.valueOf(context.resources.getColor(bottomBgList[entity.level])).let {color->
                        bottomBg.imageTintList = color
                    }
                }
                iconData = iconList
                titleData = titleList
                colorData = titleColorList
            }
        }
    }

    inner class SugarItemViewHolder(val binding: LayoutSugarItemBinding) : RecyclerView.ViewHolder(binding.root)
}