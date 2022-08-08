package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2

import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Fragment1ToUserBinding
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page1Binding
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var newPassword: String
/**
 * A simple [Fragment] subclass.
 * Use the [Part2Fragment1ToUser.newInstance] factory method to
 * create an instance of this fragment.
 */
class Part2Fragment1ToUser : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModelPart2: ViewModelPart2 by activityViewModels()
    private val sharedViewModels: SharedViewModels by activityViewModels()
    var open_old_password = 0
    var open_new_password = 0
    val TAG = "Fragment Part2Fragment1 To User"
    private lateinit var binding: FragmentPart2Fragment1ToUserBinding
    val recivedUserFromSharedPreferences: User
        get() = sharedViewModels.getUserFromSharedPreferenses()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_part2_fragment1_to_user, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Part2Fragment1ToUser.
         * text_name_user_part2_page1
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Part2Fragment1ToUser().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModels.getUserFromSharedPreferenses()
        var newPassword: String = ""
        binding = FragmentPart2Fragment1ToUserBinding.bind(view)
        binding.frag = this@Part2Fragment1ToUser
//        newPassword = binding.newPsw
        binding.vmod = sharedViewModels
        binding.lifecycleOwner = this

        binding.eyeOldPassword.setBackgroundResource(R.drawable.shut_eye)
        binding.eyeNewPassword.setBackgroundResource(R.drawable.shut_eye)
        binding.part2page1TextOldPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        binding.part2page1TextNewPassword.transformationMethod = PasswordTransformationMethod.getInstance()



        sharedViewModels.fullName.observe(viewLifecycleOwner, Observer { fullName ->
            binding.textNameUserPart2Page1.text = fullName.toString()
//            com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ARG_AGE = age.toString()
        })


    }



    fun onClickHouse(view: View) {
        findNavController().popBackStack()
    }

    fun onClickOldPassw(view: View) {
        if (open_old_password == 0) {
            binding.part2page1TextOldPassword.transformationMethod = SingleLineTransformationMethod.getInstance()
            binding.eyeOldPassword.setBackgroundResource(R.drawable.eye)
            open_old_password = 1
        } else {
            binding.eyeOldPassword.setBackgroundResource(R.drawable.shut_eye)
            binding.part2page1TextOldPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            open_old_password = 0
        }
    }

    fun onClickNewPassw(view: View) {
        if (open_new_password == 0) {
            binding.part2page1TextNewPassword.transformationMethod = SingleLineTransformationMethod.getInstance()
            binding.eyeNewPassword.setBackgroundResource(R.drawable.eye)
            open_new_password = 1
        } else {
            binding.part2page1TextNewPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.eyeNewPassword.setBackgroundResource(R.drawable.shut_eye)
            open_new_password = 0
        }
    }

    fun onClickSaveChange(view: View) {
        sharedViewModels.saveChangeNamePassword(binding.newPsw.toString())
    }
}

