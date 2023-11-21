package com.diabeat.ease.pro.activity

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.diabeat.ease.pro.BuildConfig
import com.diabeat.ease.pro.R
import com.diabeat.ease.pro.adapter.MainAdapter
import com.diabeat.ease.pro.adapter.MeAdapter
import com.diabeat.ease.pro.adapter.QaAdapter
import com.diabeat.ease.pro.adapter.SugarAdapter
import com.diabeat.ease.pro.constant.formatTimeMain
import com.diabeat.ease.pro.constant.formatTimeTwo
import com.diabeat.ease.pro.constant.formatTwo
import com.diabeat.ease.pro.constant.getPre3Days
import com.diabeat.ease.pro.constant.log
import com.diabeat.ease.pro.databinding.ActivityMainBinding
import com.diabeat.ease.pro.databinding.LayoutHomeBinding
import com.diabeat.ease.pro.databinding.LayoutMeBinding
import com.diabeat.ease.pro.databinding.LayoutQaBinding
import com.diabeat.ease.pro.databinding.LayoutSugarBinding
import com.diabeat.ease.pro.databinding.RG
import com.diabeat.ease.pro.databinding.Sugar
import com.diabeat.ease.pro.databinding.transData
import com.diabeat.ease.pro.databinding.unitList
import com.diabeat.ease.pro.db.DbHelper
import com.diabeat.ease.pro.ui.ConditionPop
import com.diabeat.ease.pro.ui.ItemBottomDecoration
import com.diabeat.ease.pro.ui.ItemLRBottomDecoration
import com.diabeat.ease.pro.util.Shared
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.text.DecimalFormat

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private var sugarAdapter = SugarAdapter(this, 2) {
        startEditActivity(it) {
            resetSugarData()
        }
    }
    private lateinit var homeBinding: LayoutHomeBinding
    private lateinit var sugarBinding: LayoutSugarBinding
    private lateinit var qaBinding: LayoutQaBinding
    private lateinit var meBinding: LayoutMeBinding

    private var chartValues: MutableList<Entry> = mutableListOf()
    private var xAxisValues: MutableList<String> = mutableListOf()
    private var maxValue = 30f
    private var minValue = 0f
    override fun initData() {
        binding.activity = this
        homeBinding = initHomeBinding()
        sugarBinding = initSugarBinding()
        qaBinding = initQaBinding()
        meBinding = initMeBinding()
        binding.vp.apply {
            adapter = MainAdapter(this@MainActivity, mutableListOf(homeBinding.root, sugarBinding.root, qaBinding.root, meBinding.root))
            addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    binding.currentIndex = position
                }

                override fun onPageScrollStateChanged(state: Int) {

                }

            })
        }
        binding.rg = RG()
        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            binding.vp.currentItem = when (id) {
                binding.rbHome.id -> 0
                binding.rbSugar.id -> 1
                binding.rbQa.id -> 2
                binding.rbMe.id -> 3
                else -> 0
            }

        }
        resetSugarData()
    }

    private fun initHomeBinding(): LayoutHomeBinding = LayoutHomeBinding.inflate(LayoutInflater.from(this@MainActivity)).apply {
        activity = this@MainActivity
        this.homeRv.apply {
            addItemDecoration(ItemLRBottomDecoration(context, 6))
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
            adapter = QaAdapter(context, type = 2) {
                startQaActivity(it)
            }
        }
    }

    private fun initSugarBinding(): LayoutSugarBinding = LayoutSugarBinding.inflate(LayoutInflater.from(this@MainActivity)).apply {
        activity = this@MainActivity
        selectTime = Shared.defaultSelectTime
        this.sugarRv.apply {
            addItemDecoration(ItemBottomDecoration(context, 12))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = sugarAdapter
            chart.apply {
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    isEnabled = true
                    setDrawGridLines(true)
                    textColor = Color.parseColor("#515151")
                    enableGridDashedLine(5f, 5f, 0f)
                    gridColor = Color.parseColor("#335566")
                    axisLineColor = Color.TRANSPARENT
                    granularity = 1f
                    valueFormatter = object : ValueFormatter() {
                        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                            return xAxisValues[value.toInt() % xAxisValues.size]
                        }
                    }
                }
                axisLeft.apply {
                    axisMinimum = minValue
                    axisMaximum = maxValue
                    setDrawGridLines(false)
                    textColor = Color.parseColor("#515151")
                    axisLineColor = Color.TRANSPARENT
                    labelCount = 6
                    valueFormatter = object : ValueFormatter() {
                        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                            return DecimalFormat("0.00").format(value.toDouble())
                        }
                    }
                }
                axisRight.isEnabled = false
                legend.isEnabled = false
                description.isEnabled = false
                isDragEnabled = true
                isHighlightPerTapEnabled = false
                isAutoScaleMinMaxEnabled = true
                isDragDecelerationEnabled = true
                setTouchEnabled(true)
                setScaleEnabled(false)
                setPinchZoom(false)
                animateY(500)
            }
        }

    }


    private fun setChartData(it: List<Sugar>) {
        chartValues.clear()
        xAxisValues.clear()
        var min: Float = it[0].transData()
        var max: Float = it[0].transData()
        for (i in it.indices) {
            "i = ${i.toFloat()} data = ${it[i].transData()} time = ${it[i].time.formatTimeTwo()}".log()
            xAxisValues.add(it[i].time.formatTimeTwo())
            chartValues.add(Entry(i.toFloat(), it[i].transData()))
            if (min - it[i].transData() > 0)
                min = it[i].transData()
            if (max - it[i].transData() < 0)
                max = it[i].transData()
        }
        if (min - 30 < 0)
            min = 0.0f
        max += 30f
        minValue = min
        maxValue = max

        sugarBinding.chart.apply {
            axisLeft.apply {
                axisMinimum = minValue
                axisMaximum = maxValue
                labelCount = if (xAxisValues.size > 6) 6 else xAxisValues.size
            }

            data = LineData(LineDataSet(chartValues, "").apply {
                color = Color.parseColor("#2A7EFD")
                lineWidth = 1f
                setDrawFilled(true)
                fillDrawable = resources.getDrawable(R.drawable.bg_chart)
                valueTextColor = Color.TRANSPARENT
                mode = LineDataSet.Mode.CUBIC_BEZIER
                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return DecimalFormat("0.00").format(value.toDouble())
                    }
                }
            })
            zoom(it.size / 10f, 1f, 0f, 0f).apply {

            }
            invalidate()
        }
    }


    private fun initQaBinding(): LayoutQaBinding = LayoutQaBinding.inflate(LayoutInflater.from(this@MainActivity)).apply {
        this.qaRv.apply {
            addItemDecoration(ItemLRBottomDecoration(context, 6))
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
            adapter = QaAdapter(context) {
                startQaActivity(it)
            }
        }
    }

    private fun initMeBinding(): LayoutMeBinding = LayoutMeBinding.inflate(LayoutInflater.from(this@MainActivity)).apply {
        this.meRv.apply {
            addItemDecoration(ItemBottomDecoration(context, 12))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MeAdapter(context) { me, position ->
                when (position) {
                    0 -> startWebActivity(me.title, BuildConfig.privacy)
                    1 -> startWebActivity(me.title, BuildConfig.policy)
                    2 -> sharedApp()
                }
            }
        }
    }


    fun startNewRecordActivity() {
        startNewActivity {
            binding.vp.currentItem = 1
            resetSugarData()
        }
    }

    private fun resetSugarData() {
        DbHelper.queryByKind(Shared.defaultSelectTime).let {
            sugarAdapter.list = it
            sugarBinding.dataRecent.text = if (it.isNotEmpty()) DecimalFormat("0.00").format(it[0].transData()) else "0.0"
            var avg: Float = 0f
            val list = it.filter { sugar ->
                sugar.time > System.currentTimeMillis().getPre3Days()
            }
            list.forEach { entity ->
                avg += entity.transData()
            }
            sugarBinding.dataAvg.text = if (list.isNotEmpty()) DecimalFormat("0.00").format(avg / list.size) else "0.0"
            if (it.isEmpty()) {
                sugarBinding.historyVisible = View.GONE
            } else {
                sugarBinding.historyVisible = View.VISIBLE
            }
            sugarBinding.currentUnit = Shared.currentUnit
            if (it.isNotEmpty()) {
                setChartData(it)
            }
        }

    }

    fun selectTime() {
        ConditionPop(this).apply {
            list = viewModel.conditionTime
            checkIndex = viewModel.conditionTime.indexOf(Shared.defaultSelectTime)
            cancelClick = {

            }
            confirmClick = {
                Shared.defaultSelectTime = it
                sugarBinding.selectTime = it
                resetSugarData()
            }
        }.show()
    }

    fun startHistoryActivity() {
        startSugarActivity()
    }
}