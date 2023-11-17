package com.diabeat.ease.pro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diabeat.ease.pro.databinding.LayoutMeItemBinding
import com.diabeat.ease.pro.databinding.Me
import com.diabeat.ease.pro.databinding.meList

class MeAdapter(
    private val context: Context,
    private val onItemClickListener: (Me,Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MeItemViewHolder(LayoutMeItemBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return meList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MeItemViewHolder) {
            holder.binding.apply {
                me = meList[position]
                itemClick = OnClickListener { onItemClickListener.invoke(meList[position],position) }
            }
        }
    }

    inner class MeItemViewHolder(val binding: LayoutMeItemBinding) : RecyclerView.ViewHolder(binding.root)
}
