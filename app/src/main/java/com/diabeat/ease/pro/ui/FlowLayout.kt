package com.diabeat.ease.pro.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup


class FlowLayout: ViewGroup{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        var modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        var sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        var modeHeight = MeasureSpec.getMode(heightMeasureSpec)
        var width = 0
        var height = 0
        var lineWidth = 0
        var lineHeight = 0

        var count = childCount
        for (i in 0 until count) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val lp = child
                .layoutParams as MarginLayoutParams

            val childWidth = (child.measuredWidth + lp.leftMargin
                    + lp.rightMargin)
            val childHeight = (child.measuredHeight + lp.topMargin
                    + lp.bottomMargin)

            if (lineWidth + childWidth > sizeWidth - paddingLeft - paddingRight) {
                width = width.coerceAtLeast(lineWidth)
                lineWidth = childWidth
                height += lineHeight
                lineHeight = childHeight
            } else {
                lineWidth += childWidth
                lineHeight = lineHeight.coerceAtLeast(childHeight)
            }
            if (i == count - 1) {
                width = lineWidth.coerceAtLeast(width)
                height += lineHeight
            }
        }
        setMeasuredDimension(
            if (modeWidth == MeasureSpec.EXACTLY) sizeWidth else width + paddingLeft + paddingRight,
            if (modeHeight == MeasureSpec.EXACTLY) sizeHeight else height + paddingTop + paddingBottom //
        )
    }

    private var mAllViews: MutableList<MutableList<View>> = mutableListOf()
    private var mLineHeight: MutableList<Int> = mutableListOf()
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        mAllViews.clear()
        mLineHeight.clear()

        var lineWidth = 0
        var lineHeight = 0
        var lineViews: MutableList<View> = mutableListOf()

        val cCount = childCount

        for (i in 0 until cCount) {
            val child = getChildAt(i)
            val lp = child.layoutParams as MarginLayoutParams
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - paddingLeft - paddingRight) {
                mLineHeight.add(lineHeight)
                mAllViews.add(lineViews)

                lineWidth = 0
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin
                lineViews = ArrayList()
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin
            lineHeight = lineHeight.coerceAtLeast(childHeight + lp.topMargin + lp.bottomMargin)
            lineViews.add(child)
        }
        mLineHeight.add(lineHeight)
        mAllViews.add(lineViews)
        var left = paddingLeft
        var top = paddingTop
        val lineNum = mAllViews.size
        for (i in 0 until lineNum) {
            lineViews = mAllViews[i]
            lineHeight = mLineHeight[i]
            for (j in lineViews.indices) {
                val child: View = lineViews[j]
                if (child.visibility == GONE) {
                    continue
                }
                val lp = child
                    .layoutParams as MarginLayoutParams
                val lc = left + lp.leftMargin
                val tc = top + lp.topMargin
                val rc = lc + child.measuredWidth
                val bc = tc + child.measuredHeight

                child.layout(lc, tc, rc, bc)
                left += (child.measuredWidth + lp.leftMargin
                        + lp.rightMargin)
            }
            left = paddingLeft
            top += lineHeight
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams? {
        return MarginLayoutParams(context, attrs)
    }

}