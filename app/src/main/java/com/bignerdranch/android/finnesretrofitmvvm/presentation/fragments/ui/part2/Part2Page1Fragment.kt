package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.util.Pair
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page1Binding
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.menu.MenuDayList
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User
import com.github.mikephil.charting.data.LineData
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private var ARG_AGE = "param1"
private var ARG_HEIGHT = "param2"
private var ARG_WEIGHT = "param3"


/**
 * A simple [Fragment] subclass.
 * Use the [Part2Page1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Part2Page1Fragment() : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var materialDatePicker: MaterialDatePicker<*>
    var dyear: Int = 0
    var dmonth: Int = 0
    var dday: Int = 0
    var new_weigt_today: String = ""

    private var param1: String? = null
    private var param2: String? = null

    private val chartStyle by lazy(LazyThreadSafetyMode.NONE) {
        SparkLineStyle(context = activity!!)
    }


    //  var chartStyle: SparkLineStyle = SparkLineStyle(this) //= SparkLineStyle(context)
    private lateinit var viewModel: ViewModelPart2// by activityViewModels()

    private val sharedViewModels: SharedViewModels by activityViewModels()
    private val menuDayPart2ViewModels: MenuDayPart2ViewModel by activityViewModels()
    val recivedUserFromSharedPreferences: User
        get() = sharedViewModels.getUserFromSharedPreferenses()


    val TAG = "Fragment Part2 Page1"
    private lateinit var binding: FragmentPart2Page1Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_AGE)
//            param2 = it.getString(ARG_HEIGHT)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_part2_page1, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Part2Page1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Part2Page1Fragment().apply {
                arguments = Bundle().apply {

                    putString(ARG_AGE, viewModel.age)
                    putString(ARG_HEIGHT, viewModel.hight)
                    putString(ARG_WEIGHT, viewModel.weight)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelPart2::class.java)
//        viewModel = (activity as MainActivity).viewModel


//        viewModel.age = ARG_AGE
//        viewModel.hight = ARG_HEIGHT
//        viewModel.weight = ARG_WEIGHT
        sharedViewModels.getStartEndData()
        binding = FragmentPart2Page1Binding.bind(view)

        binding.frag = this@Part2Page1Fragment
        binding.vmod = sharedViewModels

        binding.lifecycleOwner = this

//Log.e(TAG, MyVariable.Singleton.publicData?.age.toString())
        backToFragment()
        binding.lynDatePicker.isVisible = false
        binding.inputWeight.isVisible = false

        binding.part2page1ButtonHistoryWeight.setOnClickListener {
//            showDatePickerDialog()
            showDatePickerDialogRange()


        }
        binding.textBack.setOnClickListener {
            backToFragment()
        }

        sharedViewModels.lineDataSet.observe(viewLifecycleOwner) { lineDataSet ->
            chartStyle.styleLineDataSet(lineDataSet)
            binding.dayChart.data = LineData(lineDataSet)
            binding.dayChart.invalidate()
        }
        binding.noteButton.setOnClickListener {
            findNavController().navigate(R.id.action_part2Page1Fragment_to_menuDayPart2Fragment)
        }
        val today = Calendar.getInstance()
        binding.lynDatePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { view, year, month, day ->
            val month = month + 1
            val msg = "You Selected: $day/$month/$year"
            dmonth = month
            dday = day
            dyear = year
            sharedViewModels._data3.weight = new_weigt_today.toString()
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                .build()

        datePicker.show(parentFragmentManager, TAG)
        datePicker.addOnPositiveButtonClickListener {
            // formatting date in dd-mm-yyyy format.
            val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
            val date = dateFormatter.format(Date(it))
            Toast.makeText(context, "$date is selected", Toast.LENGTH_LONG).show()

        }

        // Setting up the event for when cancelled is clicked
        datePicker.addOnNegativeButtonClickListener {
            Toast.makeText(context, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()
        }

        // Setting up the event for when back button is pressed
        datePicker.addOnCancelListener {
            Toast.makeText(context, "Date Picker Cancelled", Toast.LENGTH_LONG).show()
        }

    }

    private fun showSmallDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                .build()

        datePicker.show(parentFragmentManager, TAG)


    }


    private fun showDatePickerDialogRange() {
        var dateRangeSelected: String
        val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Выберите даты")
            .setSelection(Pair(MaterialDatePicker.thisMonthInUtcMilliseconds(),
                MaterialDatePicker.todayInUtcMilliseconds()))
            .build()
        dateRangePicker.addOnPositiveButtonClickListener { it ->
            sharedViewModels.formatDataPicker(it)
            sharedViewModels.formatDataPickerAPI(it)
            Log.e(TAG,
                " PICKER    ${sharedViewModels.startDataAPI.value.toString()}     ${sharedViewModels.endDataAPI.value.toString()}      ")
            Log.e(TAG,
                " PICKER    ${sharedViewModels.startData.value.toString()}     ${sharedViewModels.endData.value.toString()}      ")
            menuDayPart2ViewModels.launchGetMenuList(sharedViewModels.startDataAPI.value.toString(),
                sharedViewModels.endDataAPI.value.toString())
            menuDayPart2ViewModels.recyclerListData.value?.let { it1 ->
                sharedViewModels.funListWeightForChart(it1.listMenuDay)
            }

            menuDayPart2ViewModels.getMenuListObserverable()
                .observe(viewLifecycleOwner, Observer<MenuDayList> {
                    if (it == null) {
                        Log.e(TAG, "${it}")
                    } else {
                        sharedViewModels.funListWeightForChart(it.listMenuDay)
                    }
                })


//            dateRangeSelected = outputDateFormat.format(it.first).toString() + " - " + outputDateFormat.format(it.second).toString()
//            binding.textDataRightPart2Page1.text = "Выбраны даты: ${sharedViewModels.startData} - ${sharedViewModels.endData}"
            binding.xyPlotChart.isVisible = true
            binding.parametrsButtonsChart.isVisible = false
            sharedViewModels.saveDataStartDataCalendar(startData = sharedViewModels.startData.value.toString(),
                endData = sharedViewModels.endData.value.toString())
        }

        dateRangePicker.addOnNegativeButtonClickListener {
            binding.textDataRightPart2Page1.text = ""
            Snackbar.make(binding.textDataRightPart2Page1,
                "No date is selected",
                Snackbar.LENGTH_LONG).show()
        }

        dateRangePicker.show(parentFragmentManager, "Data Picker")
    }

    private fun backToFragment() {
        Log.e(TAG, "clickkkkkkkkkkk")
        Log.e(TAG, "${sharedViewModels._data3.age}")
        binding.xyPlotChart.isVisible = false
        binding.parametrsButtonsChart.isVisible = true
    }

    @SuppressLint("SetTextI18n")
    private fun showDatePickerDialog() {
        var dateSelected: String = ""
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        picker.addOnPositiveButtonClickListener {
//            dateSelected = outputDateFormat.format(it)
            binding.textDataRightPart2Page1.text = "Selected Date: $dateSelected"
            binding.xyPlotChart.isVisible = true
            binding.parametrsButtonsChart.isVisible = false
        }

        picker.addOnNegativeButtonClickListener {
            binding.textDataRightPart2Page1.text = ""
            Snackbar.make(binding.textDataRightPart2Page1,
                "No date is selected",
                Snackbar.LENGTH_LONG).show()
        }
        picker.show(parentFragmentManager, "Data Picker")
//        picker.show(supportFragmentManager, "Date Picker")
    }

    fun onClickToSetting(view: View) {
        findNavController().navigate(R.id.action_part2Page1Fragment_to_part2Fragment1ToUser)
    }

    fun onClickHouse(view: View) {
        findNavController().popBackStack()
    }

    fun onClickProgrammPitan(view: View) {
        findNavController().navigate(R.id.action_part2Page1Fragment_to_part2Page2Fragment)
    }

    fun onClickMenuWeek(view: View) {
        findNavController().navigate(R.id.action_part2Page1Fragment_to_part2Page3Fragment)
    }

    fun onClickRegimenMeal(view: View) {
        findNavController().navigate(R.id.action_part2Page1Fragment_to_part2Page4Fragment)
    }

    fun onClickPhysicNagr(view: View) {
        findNavController().navigate(R.id.action_part2Page1Fragment_to_part2Page5Fragment)
    }

    fun onClickPrincipPitan(view: View) {
        findNavController().navigate(R.id.action_part2Page1Fragment_to_part2Page6Fragment)
    }

    fun onClickMissTake(view: View) {
        findNavController().navigate(R.id.action_part2Page1Fragment_to_part2Page7Fragment)
    }

    fun onClickHistoryWeightDiagramm(view: View) {
        binding.lynDatePicker.isVisible = true
        binding.inputWeight.isVisible = true
        binding.lynWeightHistory.isVisible = false
        binding.footerImage.isVisible = false
        binding.parametrsButtonsChart.isVisible = false
        Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
    }

}