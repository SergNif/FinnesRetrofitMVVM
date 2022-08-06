package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.login

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.data.storage.SharedPrefUserStorage
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentLoginBinding
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.MainActivity
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.MainViewModel
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2.SharedViewModels

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlin.properties.Delegates


class LoginFragment : Fragment(R.layout.fragment_login) {

    var idUser by Delegates.notNull<Int>()
    private lateinit var viewModel: MainViewModel
    private val sharedViewModels: SharedViewModels by activityViewModels()
    val TAG = "Fragment Login"
    private lateinit var binding: FragmentLoginBinding
    lateinit var emailEditText: EditText

    // This property is only valid between onCreateView and
    // onDestroyView.
//    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding = FragmentLoginBinding.bind(view)
        emailEditText = binding.Email
        emailEditText.setPressed(true)
        emailEditText.isPressed = true
        emailEditText.setClickable(true)
        emailEditText.isClickable = true
        view.setOnClickListener {
            Toast.makeText(context, "Welcome page 1", Toast.LENGTH_SHORT).show()
        }
        viewModel.registrOrLogin.value = "login"
//        return binding.root
        var loginYes: Boolean = false
        val passwordEditText = binding.password
        val loginButton = binding.loginBtn
        val loadingProgressBar = binding.loading


        val getSharedPrefs by lazy(LazyThreadSafetyMode.NONE) {
            context?.let {
                SharedPrefUserStorage(
                    it
                )
            }
        }
        val loginData = getSharedPrefs?.get()
        Log.e(TAG, "email")
        if (loginData!!.email != "no") {
            viewModel.loginDataChanged(

                loginData.email.toString(),
                loginData.password.toString()
            )
//            viewModel.createDataUser(
//                email = viewModel.liveEmailLogin.toString(),
//                password = viewModel.livePasswordLogin.toString()
//            )
        }
//        val nameObserver = Observer<String> { newName ->
//            // Update the UI, in this case, a TextView.
//            binding.Email.text = newName
//        }

        viewModel.liveEmailLogin.observe(viewLifecycleOwner, Observer { it ->
            binding.Email.setText(it)
        })
        viewModel.livePasswordLogin.observe(viewLifecycleOwner, Observer { it ->
            binding.password.setText(it)
        })

        binding.noAccountText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
//        viewModel.loginFormState.observe(viewLifecycleOwner,
//            Observer { loginFormState ->
//                if (loginFormState == null) {
//                    return@Observer
//                }
//                loginButton.isEnabled = loginFormState.isDataValid
//                loginFormState.usernameError?.let {
//                    emailEditText.error = getString(it)
//                }
//                loginFormState.passwordError?.let {
//                    passwordEditText.error = getString(it)
//                }
//            })
        binding.loginBtn.setOnClickListener {
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


                else -> {
                    val email: String = binding.Email.text.toString().trim() { it <= ' ' }
                    val password: String = binding.password.text.toString().trim() { it <= ' ' }
//                    val fullNameUs: String = fullName.text.toString().trim() { it <= ' '}
//                    textCreateAcc.text = fullNameUs


                    // Create an instanse and create a register a user with email and password.
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                            //If the registration is successfully done
                            if (task.isSuccessful) {
                                // Firebase registered user
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                Log.e(TAG, "before create user")
//                                idUser = getSharedPrefs?.getId()!!
//                                viewModel.liveIdUsersData.value = idUser
//                                if (idUser != 85000) {
//                                    viewModel.createDataUser(
//                                        email = binding.Email.text.toString(),//viewModel.liveEmailLogin.toString(),
//                                        password = binding.password.text.toString(),//viewModel.livePasswordLogin.toString()
//                                        id= idUser
//                                    )
//                                }
////                                lifecycleScope.launch{ viewModel.query()}
//                                Log.e(TAG, "before query")
////                                viewModel.query()
//                                viewModel.getQueryUserDataCall(
//                                    id = idUser,
//                                    emailLogin = binding.Email.text.toString(),//viewModel.liveEmailLogin.toString(),
//                                    passwordLogin = binding.password.text.toString(),//viewModel.livePasswordLogin.toString()
//                                    )

                                sharedViewModels.launchGetUserOfEmail(binding.Email.text.toString(),
                                    binding.password.text.toString())
                                Log.e(TAG, "after query")

                                sharedViewModels.createNewUserLiveData.observe(viewLifecycleOwner,
                                    Observer {

//                                        sharedViewModels.saveUserToSharedPreferens()
                                        findNavController().navigate(R.id.action_loginFragment_to_part2Page1Fragment)

                                        Toast.makeText(
                                            activity,
                                            "Вы успешно вошли !",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    })
                                /** здесь новый пользователь зарегистрирован и автоматически залогинен,
                                 * мы должны перебросить его на Главный экран
                                 */
//                                val intent =
//                                    Intent(
//                                        activity,
//                                        MainActivity::class.java
//                                    ) //MainActivity::class.java)
//                                intent.flags =
//                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                val mess: String = FirebaseAuth.getInstance().currentUser!!.uid
//                                Log.i("My_Firebas", mess.toString())
//                                Log.i("My_HHH_Firebas", mess.toString())
//                                intent.putExtra(
//                                    "user_id",
//                                    mess
//                                )
//                                intent.putExtra("email_id", email)
////                                intent.putExtra("full_name_us", fullNameUs)
//                                startActivity(intent)
////                                finish()
                                loginYes = true
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
//        viewModel.loginResult.observe(viewLifecycleOwner,
//            Observer { loginResult ->
//                loginResult ?: return@Observer
//                loadingProgressBar.visibility = View.GONE
//                loginResult.error?.let {
//                    showLoginFailed(it)
//                }
//                loginResult.success?.let {
//                    updateUiWithUser(it)
//                }
//            })

            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                    // ignore
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    // ignore
                }

                override fun afterTextChanged(s: Editable) {
                    viewModel.loginDataChanged(
                        binding.Email.text.toString(),
                        binding.password.text.toString()
//                        viewModel.liveEmailLogin.toString(),
//                        viewModel.livePasswordLogin.toString()
                    )
//                    viewModel.createDataUser(
//                        binding.Email.text.toString(),
//                        binding.password.text.toString(),
////                        email = viewModel.liveEmailLogin.toString(),
////                        password = viewModel.livePasswordLogin.toString()
//
//                    )
                }
            }
//        emailEditText.addTextChangedListener(afterTextChangedListener)
//        passwordEditText.addTextChangedListener(afterTextChangedListener)
//        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                viewModel.login(
//                    emailEditText.text.toString(),
//                    passwordEditText.text.toString()
//                )
//            }
//            false
//        }

//        loginButton.setOnClickListener {
//            loadingProgressBar.visibility = View.VISIBLE
//            viewModel.login(
//                emailEditText.text.toString(),
//                passwordEditText.text.toString()
//            )
//        }
//    }

//    private fun updateUiWithUser(model: LoggedInUserView) {
//        val welcome = getString(R.string.welcome) + model.displayName
//        // TODO : initiate successful logged in experience
//        val appContext = context?.applicationContext ?: return
//        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
//    }
//
//    private fun showLoginFailed(@StringRes errorString: Int) {
//        val appContext = context?.applicationContext ?: return
//        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
//    }
//            findNavController().navigate(R.id.action_loginFragment_to_page1MaFemaleDataFragment)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "destroy login fragment")
    }
}


