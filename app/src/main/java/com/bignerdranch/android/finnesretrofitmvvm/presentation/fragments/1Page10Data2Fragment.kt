package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPage10Data2Binding
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.adapter.ImageLoader
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2.Part2Page1Fragment
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2.SharedViewModels
import kotlinx.coroutines.delay

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Page10Data2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Page10Data2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: MainViewModel
    val TAG = "Fragment Page10"

    private lateinit var binding: FragmentPage10Data2Binding
    private val sharedViewModels: SharedViewModels by activityViewModels()

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
        return inflater.inflate(R.layout.fragment_page10_data2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding = FragmentPage10Data2Binding.bind(view)
        var modifiedButton: Boolean = false

        binding.vmPage10 = viewModel
        binding.imgLoadPage10 = ImageLoader()
        binding.lifecycleOwner = this

        binding.page10FastFood.setOnClickListener { viewModel.changeColorButtonPage10("1")
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page10Data2Fragment)
            }
        }
        binding.page10EatNigth.setOnClickListener { viewModel.changeColorButtonPage10("2")
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page10Data2Fragment)
            }
        }
        binding.page10FastShugar.setOnClickListener { viewModel.changeColorButtonPage10("3")
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page10Data2Fragment)
            }
        }
        binding.nothingOfList.setOnClickListener { viewModel.changeColorButtonPage10("4")
                    val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page10Data2Fragment)
            }
        }

//        val intent = Intent(context, Part2Page1Fragment::class.java)
//        intent.putExtra("people_data", viewModel.usersData)
//        startActivity(intent)



        binding.textBack.setOnClickListener{
//            if (modifiedButton){viewModel.createLocalDataPage10()}
            findNavController().popBackStack()
        }
        binding.textNext.setOnClickListener{
            Log.e(TAG, "${viewModel.dataPage3}")
            Log.e(TAG, "${viewModel.dataPage1}")
            Log.e(TAG, "${viewModel.dataPage4}")
            Log.e(TAG, "${viewModel.dataPage5}")
            Log.e(TAG, "${viewModel.dataPage6}")
            Log.e(TAG, "${viewModel.dataPage8}")
            Log.e(TAG, "${viewModel.dataPage10}")
            viewModel.updateQuery()
//            if (modifiedButton){viewModel.createLocalDataPage10()}
//                      lifecycleScope.launch{ viewModel.query()}
         //   if (sharedViewModels.getIdFromSharedPreferenses() != 85000){
//            SystemClock.sleep(1200)
            if (viewModel.dataUser.email.toString() != "testmail@@qq.qq"){
                if (sharedViewModels.isThingInitialized()){

            findNavController().navigate(R.id.action_page10Data2Fragment_to_part2Page1Fragment)}
                else{
                    sharedViewModels._user= sharedViewModels.getUserFromSharedPreferenses()
                    Log.e(TAG, "${sharedViewModels._user}")
                    findNavController().navigate(R.id.action_page10Data2Fragment_to_part2Page1Fragment)
                }
            }
            else {
                findNavController().navigate(R.id.action_page10Data2Fragment_to_loginFragment)
//            val intent =
//                Intent(activity, LoginActivity::class.java) //MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
////            Log.i("My_Firebas", mess.toString())
////            Log.i("My_HHH_Firebas", mess.toString())
//            intent.putExtra("user_id", "UserName"
////                mess
//            )
//            intent.putExtra("email_id", "UserEmail")
////                                intent.putExtra("full_name_us", fullNameUs)
//            startActivity(intent)
////            finish()
            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Page10Data2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Page10Data2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}