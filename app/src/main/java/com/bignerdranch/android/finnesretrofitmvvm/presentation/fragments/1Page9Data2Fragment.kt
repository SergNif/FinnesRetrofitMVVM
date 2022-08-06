package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPage9Data2Binding
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.adapter.ImageLoader

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Page9Data2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Page9Data2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: MainViewModel
    val TAG = "Fragment Page9"

    private lateinit var binding: FragmentPage9Data2Binding

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
        return inflater.inflate(R.layout.fragment_page9_data2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding = FragmentPage9Data2Binding.bind(view)
        var modifiedButton: Boolean = false

        binding.vmPage9 = viewModel
        binding.imgLoadPage9 = ImageLoader()
        binding.lifecycleOwner = this

        binding.page9WorkOffice.setOnClickListener { viewModel.changeColorButtonPage9("1")
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page9Data2Fragment)
            }
        }
        binding.page9InTravel.setOnClickListener { viewModel.changeColorButtonPage9("2")
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page9Data2Fragment)
            }
        }
        binding.page9DayFoot.setOnClickListener { viewModel.changeColorButtonPage9("3")
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page9Data2Fragment)
            }
        }
        binding.page9InHouse.setOnClickListener { viewModel.changeColorButtonPage9("4")
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page9Data2Fragment)
            }
        }

//        viewModel.livepage9Button1.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
//            binding.page9WorkOffice.setBackgroundResource(color)
//        })
//        viewModel.livepage9Button2.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
//            binding.page9InTravel.setBackgroundResource(color)
//        })
//        viewModel.livepage9Button3.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
//            binding.page9DayFoot.setBackgroundResource(color)
//        })
//        viewModel.livepage9Button4.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
//            binding.page9InHouse.setBackgroundResource(color)
//        })

        binding.textBack.setOnClickListener{

            findNavController().popBackStack()
        }
        binding.textNext.setOnClickListener{

            findNavController().navigate(R.id.action_page9Data2Fragment_to_page10Data2Fragment)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Page9Data2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Page9Data2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}