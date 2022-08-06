package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentMenuDayPart2Binding

import com.bignerdranch.android.finnesretrofitmvvm.domain.models.menu.MenuDay
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.menu.MenuDayList
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.adapter.RecyclerViewAdapter
import java.time.LocalDate

class MenuDayPart2Fragment : Fragment() {
    val TAG = "Fragment Part2 Menu Day Recycler Day"
    lateinit var binding: FragmentMenuDayPart2Binding
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var iview: View
    lateinit var im: ImageButton
    lateinit var plusNextDay: ImageView
    var menuList: List<MenuDay> = mutableListOf<MenuDay>()
    private val sharedViewModels: SharedViewModels by activityViewModels()


    companion object {
        fun newInstance() = MenuDayPart2Fragment()
    }

    private lateinit var viewModel: MenuDayPart2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
//        binding = FragmentMenuDayPart2Binding.inflate(inflater)
        iview = inflater.inflate(R.layout.fragment_menu_day_part2, container, false)
        im = iview.findViewById(R.id.plus_one_day_menu)
        plusNextDay = iview.findViewById(R.id.plus_next_day_menu_next)
//        initViewModel(iview)
//        generator(20)
        initRecyclerView(iview)

        //searchMenuDay()

        return iview
//        return binding.root
    }



    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuDayPart2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MenuDayPart2ViewModel::class.java)
        binding = FragmentMenuDayPart2Binding.bind(iview)
        viewModel = ViewModelProvider(this).get(MenuDayPart2ViewModel::class.java)
        viewModel.getMenuListObserverable().observe(viewLifecycleOwner, Observer<MenuDayList> {
            if (it == null) {
                Log.e(TAG, "Get Menu List NULL ${it}")
                Toast.makeText(context, "no result found...", Toast.LENGTH_LONG).show()
            } else {
//                Toast.makeText(context,     "${it.listMenuDay[3]} YES result found...",  Toast.LENGTH_LONG).show()
                Log.e(TAG, "Get Menu List NOT NULL ${it.listMenuDay}")
                sharedViewModels.funListWeightForChart(it.listMenuDay)
                recyclerViewAdapter.menuList =  it.listMenuDay.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })
        binding.frag = this@MenuDayPart2Fragment
        binding.vmod = sharedViewModels

        binding.lifecycleOwner = this

        Log.e(TAG, "Get Menu List")
//        Log.e(TAG, "${}  Get Menu List ${}")
        sharedViewModels.formatDataPickerAPI()
        viewModel.launchGetMenuList(sharedViewModels.startDataAPI.value.toString(), sharedViewModels.endDataAPI.value.toString())

        im.setOnClickListener {

            Log.e(TAG, "To next fragment")
            findNavController().navigate(R.id.action_menuDayPart2Fragment_to_newOneMenuDayFragment)
        }

//        plusNextDay.setOnClickListener {
//            viewModel.dateMenuRecyle += 1
//            viewModel.launchGetMenuList(sharedViewModels.startData.toString(), sharedViewModels.endData.toString())
//            Toast.makeText(activity,
//                "TESTING BUTTON CLICK ${viewModel.dateMenuRecyle}",
//                Toast.LENGTH_SHORT).show()
//        }
        sharedViewModels.endDataAPI.observe(viewLifecycleOwner, Observer {
            viewModel.launchGetMenuList(sharedViewModels.startDataAPI.value.toString(), sharedViewModels.endDataAPI.value.toString())
        })
    }

    fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycl)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerViewAdapter = RecyclerViewAdapter()//(menuList)
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.setOnItemClickListener(object : RecyclerViewAdapter.onItemClickListenerRecyclViewAdapter{
            override fun onItemClick(position: Int) {
                sharedViewModels.positionRecycklerViewEdit = position
                findNavController().navigate(R.id.action_menuDayPart2Fragment_to_newOneMenuDayFragment)
                Toast.makeText(activity, "click on ${position}", Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun onClickHouse(view: View) {
        findNavController().popBackStack()
    }
    fun onClickNextFragment(view: View) {
        findNavController().navigate(R.id.action_menuDayPart2Fragment_to_part2Page2Fragment)
    }

    fun onClickNextDay(view: View){
        Log.e(TAG, "onClickNextDay ${sharedViewModels.startDataAPI.value}")
        Log.e(TAG, "onClickNextDay ${sharedViewModels.endDataAPI.value}")
        sharedViewModels.oneChangeStartEndData(1)
//        sharedViewModels.startDataAPI.value?.toString().let {
//            sharedViewModels.endDataAPI.value?.toString().let { it1 ->
//                if (it != null) {
//                    if (it1 != null) {
//                        viewModel.launchGetMenuList(
//                            it,
//                            it1)
//                    }
//                }
//            }
//        }
//           LocalDate.parse(sharedViewModels.startDataAPI.value.toString()).plusDays(1).toString(),
//           LocalDate.parse(sharedViewModels.endDataAPI.value.toString()).plusDays(1).toString())
    }

    fun onClickBackDay(view: View){
        sharedViewModels.oneChangeStartEndData(-1)
//        sharedViewModels.startDataAPI.value?.toString().let {
//            sharedViewModels.endDataAPI.value?.toString().let { it1 ->
//                if (it != null) {
//                    if (it1 != null) {
//                        viewModel.launchGetMenuList(
//                            it,
//                            it1)
//                    }
//                }
//            }
//        }
//           LocalDate.parse(sharedViewModels.startDataAPI.value.toString()).plusDays(-1).toString(),
//           LocalDate.parse(sharedViewModels.endDataAPI.value.toString()).plusDays(-1).toString())
    }

}