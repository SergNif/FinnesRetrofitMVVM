package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidplot.xy.*
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page1Binding
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentWeightBinding
//import com.bignerdranch.android.finnesretrofitmvvm.domain.models.menu.ChartWeight
import com.github.mikephil.charting.charts.LineChart
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*

class WeightFragment : Fragment() {
    private lateinit var lineChart: LineChart
//    private var scoreList = ArrayList<ChartWeight>()
    companion object {
        fun newInstance() = WeightFragment()
    }

    private lateinit var viewModel: WeightViewModel
    val TAG = "Fragment Part2 Weight"
    private lateinit var binding: FragmentWeightBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weight, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeightViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeightBinding.bind(view)




        val domainLabels = arrayOf<Number>(10,20,30,60,70,80,90,100,130,140)
        val series1Numbers = arrayOf<Number>(1,4,8,12,16,32,26,29,10,13)
        val series2Numbers = arrayOf<Number>(2,8,4,7,32,16,64,12,7,10)

        val series1: XYSeries = SimpleXYSeries(Arrays.asList(* series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series 1")

        val series2: XYSeries = SimpleXYSeries(Arrays.asList(* series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series 2")
        val series1Format = LineAndPointFormatter(Color.BLUE, Color.BLACK, null,null)
        series1Format.setInterpolationParams ( CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal) )

        val series2Format = LineAndPointFormatter(Color.GREEN, Color.RED, null,null)
        series2Format.setInterpolationParams ( CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal) )

        binding.plot.addSeries(series1, series1Format)
        binding.plot.addSeries(series2, series2Format)

        binding.plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM)
            .setFormat(object : Format() {
                override fun format(obj: Any, toAppendTo: StringBuffer, pos: FieldPosition?): StringBuffer? {
                    val i = Math.round((obj as Number).toFloat())
                    return toAppendTo.append(domainLabels[i])
                }

                override fun parseObject(source: String?, pos: ParsePosition?): Any? {
                    return null
                }
            })
//        PanZoom.attach(binding.plot)

    }

}