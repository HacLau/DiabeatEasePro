package com.diabeat.ease.pro.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diabeat.ease.pro.databinding.Condition
import com.diabeat.ease.pro.databinding.LayoutConditionItemBinding
import com.diabeat.ease.pro.databinding.LayoutQaItemBinding
import com.diabeat.ease.pro.databinding.QA
import com.diabeat.ease.pro.databinding.conditionList
import com.diabeat.ease.pro.databinding.desLevelList
import com.diabeat.ease.pro.databinding.qaList

class ConditionAdapter(
    private val context: Context,
    private val onItemClickListener: (Condition) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ConditionItemViewHolder(LayoutConditionItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return conditionList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ConditionItemViewHolder) {
            holder.binding.apply {
                condition = conditionList[position]
                dataList = conditionList[position].desLevelList()
                itemClick = View.OnClickListener { onItemClickListener.invoke(conditionList[position]) }
            }

        }
    }

    inner class ConditionItemViewHolder(val binding: LayoutConditionItemBinding) : RecyclerView.ViewHolder(binding.root)
}