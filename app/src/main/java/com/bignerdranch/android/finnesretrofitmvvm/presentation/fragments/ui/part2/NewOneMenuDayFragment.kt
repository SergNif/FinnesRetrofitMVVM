package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentNewOneMenuDayBinding
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.UserMenuDay
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.MainViewModel
import java.time.LocalDateTime

class NewOneMenuDayFragment : Fragment() {
    val TAG = "Fragment NewOneMenuDayFragment"
    private lateinit var binding: FragmentNewOneMenuDayBinding

    private val sharedViewModels: SharedViewModels by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val menuDayPart2ViewModel: MenuDayPart2ViewModel by activityViewModels()
    lateinit var oneMenuDay: UserMenuDay

    companion object {
        fun newInstance() = NewOneMenuDayFragment()
    }

    private lateinit var viewModel: NewOneMenuDayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_new_one_menu_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewOneMenuDayViewModel::class.java)
        binding = FragmentNewOneMenuDayBinding.bind(view)
        // TODO: Use the ViewModel
        var userParam = mainViewModel.dataPage3
        binding.time.hint=LocalDateTime.now().toString().split(".")[0].split("T")[1].split(":").slice(0..1).joinToString(
            //   prefix = "[",
            separator = ":",
            // postfix = "]",
            //limit = 3,
            //truncated = "...",
            //transform = { it.uppercase() }
        )

        if (sharedViewModels.positionRecycklerViewEdit != null){
            binding.headerMenuDay.setText(sharedViewModels.listMenuDay[sharedViewModels.positionRecycklerViewEdit!!].data.toString())
            binding.menuDay.setText(sharedViewModels.listMenuDay[sharedViewModels.positionRecycklerViewEdit!!].menu.toString())
            binding.weightOneMenuDay.setText(sharedViewModels.listMenuDay[sharedViewModels.positionRecycklerViewEdit!!].weight.toString())

        }

        binding.enterMenuDay.setOnClickListener {

//            Toast.makeText(activity, "TESTING BUTTON CLICK 1", Toast.LENGTH_SHORT).show()
            oneMenuDay =
                viewModel.createUserMenuDay(
                    id = sharedViewModels.getIdFromSharedPreferenses(),
                    age = userParam.age,
                    date = funcData(),
                    time = funcTime(),
                    desired_weight = userParam.desired_weight.toDouble(),
                    height = userParam.height,
                    weight = binding.weightOneMenuDay.text.toString().toDouble(),
                    //userParam.weight.toDouble(),
                    fitness_id = sharedViewModels.getIdFromSharedPreferenses(),
                    header = binding.headerMenuDay.text.toString(),
                    menu = binding.menuDay.text.toString(),
                )
            if (sharedViewModels.positionRecycklerViewEdit != null) {
                viewModel.launchPostMenuDay(oneMenuDay,
                    sharedViewModels.listMenuDay[sharedViewModels.positionRecycklerViewEdit!!].id_note+1)
            }else{viewModel.launchPostMenuDay(oneMenuDay,0)}

            sharedViewModels.positionRecycklerViewEdit = null
            Log.e(TAG, "Query ${sharedViewModels.positionRecycklerViewEdit}")
            menuDayPart2ViewModel.launchGetMenuList(sharedViewModels.startDataAPI.value.toString(), sharedViewModels.endDataAPI.value.toString())
            Log.e(TAG, "Query2 ${sharedViewModels.positionRecycklerViewEdit}")
            findNavController().navigate(R.id.action_newOneMenuDayFragment_to_menuDayPart2Fragment)
        }

       binding.basketGarbage.setOnClickListener{
            if (sharedViewModels.positionRecycklerViewEdit != null) {

                viewModel.launchDeleteOneMenuDay(sharedViewModels.getIdFromSharedPreferenses(), sharedViewModels.listMenuDay[sharedViewModels.positionRecycklerViewEdit!!].id_note)
            }
            sharedViewModels.positionRecycklerViewEdit = null
            findNavController().navigate(R.id.action_newOneMenuDayFragment_to_menuDayPart2Fragment)
        }


    }

    private fun funcTime(): String {
        return LocalDateTime.now().toString().split(".")[0].split("T")[1].split(":").slice(0..1).joinToString(
            //   prefix = "[",
            separator = ":",
            // postfix = "]",
            //limit = 3,
            //truncated = "...",
            //transform = { it.uppercase() }
        )
    }

    private fun funcData(): String {
        return LocalDateTime.now().toString().split(".")[0].split("T")[0]
            }

}