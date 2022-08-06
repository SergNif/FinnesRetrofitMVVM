package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.login

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentRegisterBinding
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.MainActivity
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment() {
    //    lateinit var sharedPrefUserStorage: SharedPrefUserStorage
    lateinit var viewModel: MainViewModel
    val TAG = "Fragment Registr"

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding = FragmentRegisterBinding.bind(view)

        viewModel.registrOrLogin.value = "registr"
        val fullNameEditText = binding.fullName
        val emailEditText = binding.Email
        val passwordEditText = binding.password
        val registrButton = binding.registerBtn
        val backToLogin = binding.toLogin

        viewModel.livefullNameEmailRegister.observe(viewLifecycleOwner, Observer { it ->
            binding.fullName.setText(it)
        })

        viewModel.liveEmailLogin.observe(viewLifecycleOwner, Observer { it ->
            binding.Email.setText(it)
        })
        viewModel.livePasswordLogin.observe(viewLifecycleOwner, Observer { it ->
            binding.password.setText(it)
        })

        binding.registerBtn.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.Email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        activity,
                        "Пожалуйста введите Email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        activity,
                        "Пожалуйста введите Пароль",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.fullName.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        activity,
                        "Как к Вам обращаться? Имя.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val emailFB: String = binding.Email.text.toString().trim() { it <= ' ' }
                    val passwordFB: String = binding.password.text.toString().trim() { it <= ' ' }
                    val fullName = binding.fullName.text.toString().trim() { it <= ' ' }
//                    val fullName = viewModel.livefullNameEmailRegister.toString().trim() { it <= ' ' }
//                    val email = viewModel.liveemailRegister.toString().trim() { it <= ' ' }
//                    val passwordFB = viewModel.livepasswordRegister.toString().trim() { it <= ' ' }
//                    binding.textCreateAcc.text = fullName


                    // Create an instanse and create a register a user with email and password.
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(emailFB, passwordFB)
                        .addOnCompleteListener { task ->
                            //If the registration is successfully done
                            if (task.isSuccessful) {
                                // Firebase registered user
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    activity,
                                    "Вы успешно зарегистрированы !",
                                    Toast.LENGTH_SHORT
                                ).show()
                                /**
                                 * Здесь пользователь авторизован и добавлен в Аутентификацию
                                 * добавим данные в Realtime Database
                                 */
//                            val fullName = binding.fullName.text.toString()
//                            val lastName = binding.lastName.text.toString()
//                            val passwordF = binding.password.text.toString()
                                val email = binding.Email.text.toString()
//                                val fullName = viewModel.livefullNameEmailRegister.toString()
//                                val email = viewModel.liveemailRegister.toString()
//                                val passwordFB = viewModel.livepasswordRegister.toString()

                                database = FirebaseDatabase.getInstance().getReference("UserData")
                                val UserDat = User(
                                    fullName = fullName,
                                    email = emailFB,
                                    password = passwordFB
                                )

                                database.child(fullName).setValue(UserDat).addOnSuccessListener {

                                    binding.fullName.text.clear()
//                                binding.lastName.text.clear()
                                    binding.password.text.clear()
                                    binding.Email.text.clear()

                                    Toast.makeText(
                                        activity,
                                        "Successfully Saved",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }.addOnFailureListener {

                                    Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show()
                                }
//                    val fullName = viewModel.livefullNameEmailRegister.toString().trim() { it <= ' ' }
//                    val email = viewModel.liveemailRegister.toString().trim() { it <= ' ' }
//                    val passwordFB = viewModel.livepasswordRegister.toString().trim() { it <= ' ' }
                                /** здесь новый пользователь зарегистрирован и автоматически залогинен,
                                 * мы должны перебросить его на Главный экран
                                 */
//                                val sharedPrefUserStorage by lazy(LazyThreadSafetyMode.NONE) { context?.let {
//                                    SharedPrefUserStorage(
//                                        it
//                                    )
//                                } }
//                                sharedPrefUserStorage?.save(UserDat)
//                                viewModel.idUsersData.value = sharedPrefUserStorage?.getId()
                                //                                    lifecycleScope.launch{ viewModel.query()}
                                Log.e(TAG, "before create user")
                                viewModel.createDataUser(
                                    email = emailFB,
                                    password = passwordFB, fullName = fullName,
                                    id = 1
                                )
                                viewModel.saveUserToSharedPreferens(
                                    fullName = fullName,
                                    email = email,
                                    password = passwordFB
                                )
//                                lifecycleScope.launch{ viewModel.query()}
                                Log.e(TAG, "before query")
                                viewModel.query()
                                Log.e(TAG, "after query")


//                                Toast.makeText(
//                                    activity,
//                                    "Successfully Saved",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                    lifecycleScope.launch{ viewModel.query()}
//                                val sharedPrefUserStorage by lazy(LazyThreadSafetyMode.NONE) { context?.let {
//                                    SharedPrefUserStorage(
//                                        it
//                                    )
//                                } }
//                                sharedPrefUserStorage?.save(UserDat)
//                                sharedPrefUserStorage?.saveId(viewModel.liveIdUsersData.toString().toInt())
                                findNavController().navigate(R.id.action_registerFragment_to_part2Page1Fragment)
//                                val intent =
//                                    Intent(activity, MainActivity::class.java)
//                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                intent.putExtra("user_id", firebaseUser.uid)
//                                intent.putExtra("email_id", email)
//                                intent.putExtra("full_name_us", fullName)
//                                startActivity(intent)
//                                finish()
                            } else {
                                // Если регистрация не прошла, покажем ошибку
                                Toast.makeText(
                                    activity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }

            }


            val afterTextChangedListener = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // ignore
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    // ignore
                }

                override fun afterTextChanged(s: Editable) {
                    viewModel.loginDataChangedReg(
                        binding.fullName.text.toString(),
                        binding.Email.text.toString(),
                        binding.password.text.toString()
//                        viewModel.livefullNameEmailRegister.toString(),
//                        viewModel.liveemailRegister.toString(),
//                        viewModel.livepasswordRegister.toString()
                    )
                }
            }
            binding.toLogin.setOnClickListener {
//            val intent =  Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
//        usernameEditText.addTextChangedListener(afterTextChangedListener)
//        passwordEditText.addTextChangedListener(afterTextChangedListener)
//        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                loginViewModel.login(
//                    usernameEditText.text.toString(),
//                    passwordEditText.text.toString()
//                )
//            }
//            false
//        }
//
//        loginButton.setOnClickListener {
//            loadingProgressBar.visibility = View.VISIBLE
//            loginViewModel.login(
//                usernameEditText.text.toString(),
//                passwordEditText.text.toString()
//            )
//        }
        }

//    private fun updateUiWithUser(model: LoggedInUserView) {
//        val welcome = getString(R.string.welcome) + model.displayName
//        // TODO : initiate successful logged in experience
//        val appContext = context?.applicationContext ?: return
//        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
//    }

        fun showLoginFailed(@StringRes errorString: Int) {
            val appContext = context?.applicationContext ?: return
            Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "Fragmet registr destroy")
    }
}