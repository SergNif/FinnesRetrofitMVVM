package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

import android.view.View

import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.data.storage.SharedPrefUserStorage
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPage1MaFemaleDataBinding
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.adapter.ImageLoader


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
const val ARG_OBJECT = "object"

/**
 * A simple [Fragment] subclass.
 * Use the [Page1MaleFemaleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Page1MaFemaleDataFragment : Fragment(R.layout.fragment_page1_ma_female_data) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var emailLogin: String
    lateinit var passwordLogin: String


    lateinit var viewModel: MainViewModel
    val TAG = "Fragment Page1"
    lateinit var binding: FragmentPage1MaFemaleDataBinding

    private lateinit var txt_ok_http: TextView

    val getSharedPrefs by lazy(LazyThreadSafetyMode.NONE) {
        context?.let {
            SharedPrefUserStorage(
                it
            )
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        binding = FragmentPage1MaFemaleDataBinding.bind(view)
        view.setOnClickListener {
            Toast.makeText(context, "Welcome page 1", Toast.LENGTH_SHORT).show()
        }
        binding.viewModel = viewModel
        binding.imageLoader = ImageLoader()
        binding.lifecycleOwner = this
//        binding.datap
if (viewModel.isDataUserInitialized()){
    if (viewModel.dataUser.email != "testmail@@qq.qq"){
        findNavController().navigate(R.id.action_page2Data1Fragment_to_part2Page1Fragment)}}
//        var recivedIdUserFromSharedPreferences = viewModel.getIdFromSharedPreferenses()
//        var recivedUserFromSharedPreferences = viewModel.getUserFromSharedPreferenses()
//
//        if ((recivedIdUserFromSharedPreferences > 0) and (recivedIdUserFromSharedPreferences !=85000)){
//            recivedUserFromSharedPreferences.passwordFB?.let {
//                recivedUserFromSharedPreferences.email?.let { it1 ->
//                    viewModel.getQueryUserDataCall(
//                        id = recivedIdUserFromSharedPreferences,
//                        emailLogin = it1,
//                        passwordLogin = it
//                    )
//                }
//            }
//        }
//        Log.e(TAG, " USER data ${viewModel.usersData.toString()}")
        viewModel.liveIdUsersData.observe(viewLifecycleOwner, Observer { it ->
            viewModel.getQueryUserDataCall(
                it,
                viewModel.liveEmailLogin.value.toString(),
                viewModel.livePasswordLogin.value.toString()
            )
            Log.e(TAG, viewModel.registrOrLogin.value.toString())
            Log.e(TAG, viewModel.getIdFromSharedPreferenses().toString())
            Log.e(TAG, it.toString())
        })



        if (viewModel.registrOrLogin.value.toString() == "login") {
            val loginData = getSharedPrefs?.get()

            if (loginData!!.email != "no") {
                emailLogin = loginData.email.toString()
                passwordLogin = loginData.password.toString()
                val idUser: Int = viewModel.getIdFromSharedPreferenses()
                Log.e(TAG, "inside if login $idUser")
                viewModel.getQueryUserDataCall(idUser, emailLogin, passwordLogin)
            }
        }

//        if (viewModel.registrOrLogin.value.toString() == "registr") {
//            getSharedPrefs?.saveId(viewModel.liveIdUsersData.value.toString().toInt())
//        }
//Log.e(TAG, viewModel.registrOrLogin.value.toString())
//Log.e(TAG,   viewModel.getIdFomApi().toString())


        binding.textViewGirl.setOnClickListener {
//            binding.textViewGirl.setBackgroundColor(Color.parseColor("#55CC80"))
//            binding.textViewBoy.setBackgroundColor(Color.parseColor("#FF3695F4"))
            this.viewModel.clickGirl()
                        val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page1MaFemaleDataFragment)
            }
            openBox(Color.YELLOW)
        }
        binding.textViewBoy.setOnClickListener {
//            binding.textViewBoy.setBackgroundColor(Color.parseColor("#55CC80"))
//            binding.textViewGirl.setBackgroundColor(Color.parseColor("#FF3695F4"))
            this.viewModel.clickBoy()
                        val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.page1MaFemaleDataFragment)
            }
            openBox(Color.BLUE)
        }

        viewModel.liveUsersData.observe(viewLifecycleOwner, Observer { color ->

            Log.e(TAG, " USER data ${color.toString()}")
        })
    //        viewModel.liveColorPressed.observe(viewLifecycleOwner, Observer { color ->
//            binding.textViewGirl.setBackgroundResource(color)
//        })
//
//        viewModel.liveColorNoPressed.observe(viewLifecycleOwner, Observer { color ->
//            binding.textViewBoy.setBackgroundResource(color)
//        })


//            .resColorPressed.observe(this, Observer{ color ->
//            binding.textViewGirl.setBackgroundColor(color)
//
//        }

        //для изучения Kotlin передача данных в предыдущий фрагмент
//        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("asd")
//            ?.observe(viewLifecycleOwner) { result ->
//                Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
//            }
//        parentFragmentManager.setFragmentResultListener(
//            Page2Data1Fragment.REQUEST_CODE,
//            viewLifecycleOwner
//        ) { _, data ->
//            val number = data.getInt(Page2Data1Fragment.EXTRA_RANDOM_NUMBER)
//            Toast.makeText(requireContext(), "Generated number: $number", Toast.LENGTH_SHORT).show()
//        }
//
    }

    private fun openBox(color: Int) {
        Log.e(TAG, "man ${viewModel.dataPage1.man}  woman ${viewModel.dataPage1.woman}")
        findNavController().navigate(
            R.id.action_page1MaFemaleDataFragment_to_page2Data1Fragment,
            bundleOf(Page2Data1Fragment.ARG_COLOR to color)
            //animation
        )
    }

}


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }

//        txt_ok_http = binding.okhttpTxt


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_page1_ma_female_data, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment Page1MaleFemaleFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            Page1MaFemaleDataFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }