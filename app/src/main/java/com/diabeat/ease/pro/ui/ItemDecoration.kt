package com.diabeat.ease.pro.ui

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.diabeat.ease.pro.constant.dp2px

class ItemBottomDecoration(
    private val context: Context,
    private val itemGap: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        itemGap.dp2px(context).let {
            outRect.bottom = it
        }
    }
}

class ItemLRBottomDecoration(
    private val context: Context,
    private val itemGap: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        itemGap.dp2px(context).let {
            outRect.left = it
            outRect.right = it
            outRect.bottom = it
        }
    }
}