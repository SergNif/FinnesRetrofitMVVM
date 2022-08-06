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
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPage6Data2Binding
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.adapter.ImageLoader

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Page6Data2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Page6Data2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: MainViewModel
    val TAG = "Fragment Page6"

    private lateinit var binding: FragmentPage6Data2Binding

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
        return inflater.inflate(R.layout.fragment_page6_data2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding = FragmentPage6Data2Binding.bind(view)

        var modifiedButton: Boolean = false
        binding.vmPage6 = viewModel
        binding.imgLoadPage6 = ImageLoader()
        binding.lifecycleOwner = this


        binding.textPage6Zukini.setOnClickListener { viewModel.changeColorButtonPage61()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page6Data2Fragment)
            }

        }
        binding.textPage6Tomato.setOnClickListener { viewModel.changeColorButtonPage62()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page6Data2Fragment)
            }

        }
        binding.textPage6Baklagan.setOnClickListener { viewModel.changeColorButtonPage63()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page6Data2Fragment)
            }

        }
        binding.textPage6ColorCabbage.setOnClickListener { viewModel.changeColorButtonPage64()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page6Data2Fragment)
            }

        }
        binding.textPage6Ogurz.setOnClickListener { viewModel.changeColorButtonPage65()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page6Data2Fragment)
            }

        }
        binding.textPage6Broccoli.setOnClickListener { viewModel.changeColorButtonPage66()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page6Data2Fragment)
            }

        }
        binding.page5ButtonWithoutMeat.setOnClickListener { viewModel.changeColorButtonPage67()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page6Data2Fragment)
            }

        }
        binding.textPage5WithoutFish.setOnClickListener { viewModel.changeColorButtonPage68()
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page6Data2Fragment)
            }

        }

//        viewModel.livepage6Button1.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6Zukini.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6Zukini.setBackgroundResource(color)
//        })
//        viewModel.livepage6Button2.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6Tomato.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6Tomato.setBackgroundResource(color)
//        })
//        viewModel.livepage6Button3.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6Baklagan.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6Baklagan.setBackgroundResource(color)
//        })
//        viewModel.livepage6Button4.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6ColorCabbage.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6ColorCabbage.setBackgroundResource(color)
//        })
//        viewModel.livepage6Button5.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6Ogurz.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6Ogurz.setBackgroundResource(color)
//        })
//        viewModel.livepage6Button6.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage6Broccoli.setBackgroundColor(Color.parseColor(color))
//            binding.textPage6Broccoli.setBackgroundResource(color)
//        })
//        viewModel.livepage6Button7.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.page5ButtonWithoutMeat.setBackgroundColor(Color.parseColor(color))
//            binding.page5ButtonWithoutMeat.setBackgroundResource(color)
//        })
//        viewModel.livepage6Button8.observe(viewLifecycleOwner, Observer { color ->
//            modifiedButton = true
////            binding.textPage5WithoutFish.setBackgroundColor(Color.parseColor(color))
//            binding.textPage5WithoutFish.setBackgroundResource(color)
//        })

        binding.textBack.setOnClickListener{
//            if (modifiedButton){viewModel.createLocalDataPage6()}
            findNavController().popBackStack()
        }
        binding.textNext.setOnClickListener{
//            if (modifiedButton){viewModel.createLocalDataPage6()}
            findNavController().navigate(R.id.action_page6Data2Fragment_to_page7Data2Fragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Page6Data2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Page6Data2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}