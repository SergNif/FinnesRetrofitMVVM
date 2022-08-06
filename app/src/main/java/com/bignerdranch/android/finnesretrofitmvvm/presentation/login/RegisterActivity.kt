package com.bignerdranch.android.finnesretrofitmvvm.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.finnesretrofitmvvm.databinding.ActivityRegisterBinding
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User

import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toLogin.setOnClickListener {
//            val intent =  Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//            onBackPressed()
        }

        binding.registerBtn.setOnClickListener{
            when {
                TextUtils.isEmpty(binding.Email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Пожалуйста введите Email",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Пожалуйста введите Пароль",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(binding.fullName.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Как к Вам обращаться? Имя.",
                        Toast.LENGTH_SHORT
                    ).show()
                }


                else -> {
                    val emailFB:String = binding.Email.text.toString().trim() { it <= ' '}
                    val passwordFB:String = binding.password.text.toString().trim() { it <= ' '}
                    val fullName= binding.fullName.text.toString().trim() { it <= ' '}
//                    binding.textCreateAcc.text = fullName


                    // Create an instanse and create a register a user with email and password.
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(emailFB, passwordFB).addOnCompleteListener  { task ->
                        //If the registration is successfully done
                        if (task.isSuccessful) {
                            // Firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            Toast.makeText(
                                this@RegisterActivity,
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

                            database = FirebaseDatabase.getInstance().getReference("UserData")
                            val UserDat = User(fullName=fullName, email = email, password = passwordFB)//, email, email)
                            database.child(fullName).setValue(UserDat).addOnSuccessListener {

                                binding.fullName.text.clear()
//                                binding.lastName.text.clear()
                                binding.password.text.clear()
                                binding.Email.text.clear()

                                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

                            }.addOnFailureListener {

                                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                            }

                            /** здесь новый пользователь зарегистрирован и автоматически залогинен,
                             * мы должны перебросить его на Главный экран
                             */
                            val intent =
                                Intent(this@RegisterActivity, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", firebaseUser.uid)
                            intent.putExtra("email_id", email)
                            intent.putExtra("full_name_us", fullName)
                            startActivity(intent)
                            finish()
                        } else{
                            // Если регистрация не прошла, покажем ошибку
                            Toast.makeText(
                                this@RegisterActivity,
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