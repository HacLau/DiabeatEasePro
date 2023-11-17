package com.diabeat.ease.pro.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diabeat.ease.pro.databinding.LayoutQaItemBinding
import com.diabeat.ease.pro.databinding.QA
import com.diabeat.ease.pro.databinding.qaList

class QaAdapter(
    private val context: Context,
    private var type: Int = 1,
    private val onItemClickListener: (QA) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return QaItemViewHolder(LayoutQaItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return if (type == 1) qaList.size else {
            if (qaList.size > 6) 6 else qaList.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is QaItemViewHolder) {
            holder.binding.apply {
                qa = qaList[position]
                itemClick = View.OnClickListener { onItemClickListener.invoke(qaList[position]) }
                ColorStateList.valueOf(context.resources.getColor(qaList[position].bg)).let {
                    qaItemBg.imageTintList = it
                }
            }
        }
    }

    inner class QaItemViewHolder(val binding: LayoutQaItemBinding) : RecyclerView.ViewHolder(binding.root)
}