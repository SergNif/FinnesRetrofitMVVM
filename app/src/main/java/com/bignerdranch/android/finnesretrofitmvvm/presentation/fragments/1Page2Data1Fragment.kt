package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPage1MaFemaleDataBinding
//import com.bignerdranch.android.finnesretrofitmvvm.SwipeActivity
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPage2Data1Binding
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Page2Data1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Page2Data1Fragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: MainViewModel
    val TAG = "Fragment Page2"
private lateinit var binding: FragmentPage2Data1Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page2_data1, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        binding = FragmentPage2Data1Binding.bind(view)

        val color = requireArguments().getInt(ARG_COLOR)
//        binding.root.setBackgroundColor(color)
        Log.e(TAG, "man ${viewModel.dataPage1.man}  woman ${viewModel.dataPage1.woman}")
binding.textBack.setOnClickListener{
    findNavController().popBackStack()
}
        binding.textNext.setOnClickListener{
    findNavController().navigate(R.id.action_page2Data1Fragment_to_page3Data2Fragment)
}
        //для изучения Kotlin передача данных в предыдущий фрагмент
//        binding.picPage2Image.setOnClickListener{
//            val number = Random.nextInt(100)
//            findNavController().previousBackStackEntry?.savedStateHandle?.set("asd", number)
//        }
        //передача данных в предыдущий фрагмент
            binding.picPage2Image.setOnClickListener{
            val number = Random.nextInt(100)
                parentFragmentManager.setFragmentResult(REQUEST_CODE, bundleOf(EXTRA_RANDOM_NUMBER to number))
            findNavController().popBackStack()
        }

}
    companion object{
        const val ARG_COLOR = "color"

        const val REQUEST_CODE = "RANDOM_NUMBER_REQUEST_CODE"
        const val EXTRA_RANDOM_NUMBER = "EXTRA_RANDOM_NUMBER"
    }

}