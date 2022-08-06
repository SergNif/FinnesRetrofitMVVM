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
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPage7Data2Binding
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.adapter.ImageLoader

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Page7Data2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Page7Data2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: MainViewModel
    val TAG = "Fragment Page6"

    private lateinit var binding: FragmentPage7Data2Binding

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
        return inflater.inflate(R.layout.fragment_page7_data2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding = FragmentPage7Data2Binding.bind(view)

        var modifiedButton: Boolean = false

        binding.vmPage7 = viewModel
        binding.imgLoadPage7 = ImageLoader()
        binding.lifecycleOwner = this

        binding.textPage6Egg.setOnClickListener { viewModel.changeColorButtonPage71()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page7Data2Fragment)
            }
        }
        binding.textPage6Chees.setOnClickListener { viewModel.changeColorButtonPage72()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page7Data2Fragment)
            }
        }
        binding.textPage6Nut.setOnClickListener { viewModel.changeColorButtonPage73()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page7Data2Fragment)
            }
        }
        binding.textPage6CottageCheese.setOnClickListener { viewModel.changeColorButtonPage74()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page7Data2Fragment)
            }
        }
        binding.textPage6Kefir.setOnClickListener { viewModel.changeColorButtonPage75()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page7Data2Fragment)
            }
        }
        binding.textPage6Yogurt.setOnClickListener { viewModel.changeColorButtonPage76()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page7Data2Fragment)
            }
        }

//        viewModel.livepage7Button1.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6Egg.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6Egg.setBackgroundResource(color)
//        })
//        viewModel.livepage7Button2.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6Chees.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6Chees.setBackgroundResource(color)
//        })
//        viewModel.livepage7Button3.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6Nut.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6Nut.setBackgroundResource(color)
//        })
//        viewModel.livepage7Button4.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6CottageCheese.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6CottageCheese.setBackgroundResource(color)
//        })
//        viewModel.livepage7Button5.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6Kefir.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6Kefir.setBackgroundResource(color)
//        })
//        viewModel.livepage7Button6.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6Yogurt.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6Yogurt.setBackgroundResource(color)
//        })




        binding.textBack.setOnClickListener{
//            if (modifiedButton){viewModel.createLocalDataPage7()}
            findNavController().popBackStack()
        }
        binding.textNext.setOnClickListener{
//            if (modifiedButton){viewModel.createLocalDataPage7()}
            findNavController().navigate(R.id.action_page7Data2Fragment_to_page8Data2Fragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Page7Data2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Page7Data2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}