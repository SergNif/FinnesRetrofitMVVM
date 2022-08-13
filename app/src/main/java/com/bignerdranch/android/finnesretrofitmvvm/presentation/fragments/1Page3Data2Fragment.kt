package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPage3Data2Binding
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.data.DataPage3
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2.SharedViewModels

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private var ARG_AGE = "param1"
private var ARG_HEIGHT = "param2"
private var ARG_WEIGHT = "param3"


/**
 * A simple [Fragment] subclass.
 * Use the [Page3Data2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Page3Data2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    lateinit var viewModel: MainViewModel
    val TAG = "Fragment Page3"
    private lateinit var binding: FragmentPage3Data2Binding

    private val sharedViewModels: SharedViewModels by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = viewModel.dataPage3.age.toString()
            param2 = viewModel.dataPage3.height.toString()
            param3 = viewModel.dataPage3.weight.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page3_data2, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Page3Data2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Page3Data2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_AGE, param1)
                    putString(ARG_HEIGHT, param2)
                    putString(ARG_WEIGHT, param3)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding = FragmentPage3Data2Binding.bind(view)

//        arguments?.let {
//            param1 = viewModel.dataPage3.age.toString()
//            param2 = viewModel.dataPage3.height.toString()
//            param2 = viewModel.dataPage3.weight.toString()
//        }


        ARG_AGE = viewModel.dataPage3.age.toString()
        ARG_HEIGHT = viewModel.dataPage3.height.toString()
        ARG_WEIGHT = viewModel.dataPage3.weight.toString()
        binding.vmod = viewModel
//        binding.imageLoader = ImageLoader()
        binding.lifecycleOwner = this
//        view.setOnClickListener {
//            Toast.makeText(context, "Welcome page 3", Toast.LENGTH_SHORT).show()
//        }


//        binding.dataHeartAgePage3.setOnClickListener { viewModel.changeAge(binding.dataHeartAgePage3.text.toString()) }
//        binding.dataHightPage3.setOnClickListener { viewModel.changeHight(binding.dataHightPage3.text.toString()) }
//        binding.dataWeightPage3.setOnClickListener { viewModel.changeWeght(binding.dataWeightPage3.text.toString()) }
//        binding.dataDiceweightPage3.setOnClickListener { viewModel.changeDiceWeght(binding.dataDiceweightPage3.text.toString()) }
        binding.textNext.setOnClickListener {
            if (
                (binding.dataHeartAgePage3.text.isNotEmpty()) and
                (binding.dataHightPage3.text.isNotEmpty()) and
                (binding.dataWeightPage3.text.isNotEmpty()) and
                (binding.dataDiceweightPage3.text.isNotEmpty())
                            ) {
                viewModel.changeAge(binding.dataHeartAgePage3.text.toString())
                viewModel.changeHight(binding.dataHightPage3.text.toString())
                viewModel.changeWeght(binding.dataWeightPage3.text.toString())
                viewModel.changeDiceWeght(binding.dataDiceweightPage3.text.toString())
                createVariablesInSharedViewModels()
                viewModel.launchUpdateDataPage3()
                findNavController().navigate(R.id.action_page3Data2Fragment_to_page4Data2Fragment)
            } else {
                when {
                    binding.dataHeartAgePage3.text.isEmpty() -> Toast.makeText(context,
                        "Пожалуйста, введите возраст",
                        Toast.LENGTH_SHORT).show()
                    binding.dataHightPage3.text.isEmpty() -> Toast.makeText(context,
                        "Пожалуйста, введите рост",
                        Toast.LENGTH_SHORT).show()
                    binding.dataWeightPage3.text.isEmpty() -> Toast.makeText(context,
                        "Пожалуйста, введите вес",
                        Toast.LENGTH_SHORT).show()
                    binding.dataDiceweightPage3.text.isEmpty() -> Toast.makeText(context,
                        "Пожалуйста, введите желаемый вес",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.textBack.setOnClickListener {
            if (
                (binding.dataHeartAgePage3.text.isNotEmpty()) and
                (binding.dataHightPage3.text.isNotEmpty()) and
                (binding.dataWeightPage3.text.isNotEmpty()) and
                (binding.dataDiceweightPage3.text.isNotEmpty())
            ) {
                viewModel.changeAge(binding.dataHeartAgePage3.text.toString())
                viewModel.changeHight(binding.dataHightPage3.text.toString())
                viewModel.changeWeght(binding.dataWeightPage3.text.toString())
                viewModel.changeDiceWeght(binding.dataDiceweightPage3.text.toString())

                findNavController().popBackStack()
            } else {
                when {
                    binding.dataHeartAgePage3.text.isEmpty() -> Toast.makeText(context,
                        "Пожалуйста, введите возраст",
                        Toast.LENGTH_SHORT).show()
                    binding.dataHightPage3.text.isEmpty() -> Toast.makeText(context,
                        "Пожалуйста, введите рост",
                        Toast.LENGTH_SHORT).show()
                    binding.dataWeightPage3.text.isEmpty() -> Toast.makeText(context,
                        "Пожалуйста, введите вес",
                        Toast.LENGTH_SHORT).show()
                    binding.dataDiceweightPage3.text.isEmpty() -> Toast.makeText(context,
                        "Пожалуйста, введите желаемый вес",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
        fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

        viewModel.liveAgeDataPage3.observe(viewLifecycleOwner, Observer { age ->
            binding.dataHeartAgePage3.text = age.toEditable()
            ARG_AGE = age.toString()
        })
        viewModel.liveHeightDataPage3.observe(viewLifecycleOwner, Observer { hight ->
            binding.dataHightPage3.setText(hight.toEditable())
            ARG_HEIGHT = hight.toString()
        })
        viewModel.liveWeighDataPage3.observe(viewLifecycleOwner, Observer { weight ->
            binding.dataWeightPage3.setText(weight.toEditable())
            ARG_WEIGHT = weight.toString()
        })
        viewModel.liveDiceWeightDataPage3.observe(viewLifecycleOwner, Observer { dweight ->
            binding.dataDiceweightPage3.setText(dweight.toEditable())
        })
        viewModel.liveUsersData.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "observe createNewUserLiveData ${viewModel.liveUsersData.value.toString()}")
        })


    }


    private fun createVariablesInSharedViewModels() {
        sharedViewModels.sharedData3(sharedViewModels.getIdFromSharedPreferenses(), binding.dataHeartAgePage3.text.toString().toInt(),
                binding.dataHightPage3.text.toString().toInt(),
                binding.dataWeightPage3.text.toString(),
                binding.dataDiceweightPage3.text.toString()
        )

    }
}