package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import java.security.AccessController.getContext
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class SparkLineStyle(val context: Context) {

    companion object {
        private val timeFormatter = DateTimeFormatter.ofPattern("H:mm")

    }

    fun styleChart(lineChart: LineChart) = lineChart.apply {
        axisRight.isEnabled = false

        axisLeft.apply {
            isEnabled = false
            axisMinimum = 0f
            axisMaximum = 11f
        }

        xAxis.apply {
            axisMinimum = 0f
            axisMaximum = 23f
            isGranularityEnabled = true
            granularity = 4f
            setDrawGridLines(false)
            setDrawAxisLine(false)
            position = XAxis.XAxisPosition.BOTTOM

            valueFormatter = TimeValueFormatter()
            typeface = ResourcesCompat.getFont(context, R.font.barlow_semi_condensed_regular)
            textColor = ContextCompat.getColor(context, com.androidplot.R.color.off_white)
        }

        setTouchEnabled(true)
        isDragEnabled = true
        setScaleEnabled(false)
        setPinchZoom(false)

        description = null
        legend.isEnabled = false
    }

    fun styleLineDataSet(lineDataSet: LineDataSet) = lineDataSet.apply {
        color =  ContextCompat.getColor(context, com.androidplot.R.color.off_white)
        valueTextColor = ContextCompat.getColor(context, com.androidplot.R.color.off_white)
        setDrawValues(false)
        lineWidth = 3f
        isHighlightEnabled = true
        setDrawHighlightIndicators(false)
        setDrawCircles(false)
        mode = LineDataSet.Mode.CUBIC_BEZIER

        setDrawFilled(true)
        fillDrawable = ContextCompat.getDrawable(context, R.drawable.fon_xyplot)
    }

    private class TimeValueFormatter : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            val localTime = LocalTime.of(value.toInt(), 0)
            return timeFormatter.format(localTime)
        }
    }
}




