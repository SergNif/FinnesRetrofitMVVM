package com.bignerdranch.android.finnesretrofitmvvm.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.bignerdranch.android.finnesretrofitmvvm.databinding.ActivityLoginBinding
//import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.FragmActivity
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.MainActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.noAccountText.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.loginBtn.setOnClickListener{
            when {
                TextUtils.isEmpty(binding.Email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Пожалуйста введите Email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Пожалуйста введите Пароль",
                        Toast.LENGTH_SHORT
                    ).show()
                }

//                TextUtils.isEmpty(fullName.text.toString().trim{ it <= ' '}) -> {
//                    Toast.makeText(
//                        this@MainActivity,
//                        "Как к Вам обращаться? Имя.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }


                else -> {
                    val email: String = binding.Email.text.toString().trim() { it <= ' '}
                    val password: String = binding.password.text.toString().trim() { it <= ' '}
//                    val fullNameUs: String = fullName.text.toString().trim() { it <= ' '}
//                    textCreateAcc.text = fullNameUs


                    // Create an instanse and create a register a user with email and password.
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        //If the registration is successfully done
                        if (task.isSuccessful) {
                            // Firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            Toast.makeText(
                                this@LoginActivity,
                                "Вы успешно вошли !",
                                Toast.LENGTH_SHORT
                            ).show()
                            /** здесь новый пользователь зарегистрирован и автоматически залогинен,
                             * мы должны перебросить его на Главный экран
                             */


                            val intent =
                                Intent(this@LoginActivity, MainActivity::class.java) //MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            val mess: String = FirebaseAuth.getInstance().currentUser!!.uid
                            Log.i("My_Firebas", mess.toString())
                            Log.i("My_HHH_Firebas", mess.toString())
                            intent.putExtra("user_id",
                                mess
                            )
                            intent.putExtra("email_id", email)
//                                intent.putExtra("full_name_us", fullNameUs)
                            startActivity(intent)
                            finish()
                        } else{
                            // Если регистрация не прошла, покажем ошибку
                            Toast.makeText(
                                this@LoginActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
        }





    }
}