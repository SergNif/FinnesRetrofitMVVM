package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider

import com.bignerdranch.android.finnesretrofitmvvm.data.db.UserdataDatabase
import com.bignerdranch.android.finnesretrofitmvvm.data.repository.MainRepository
import com.bignerdranch.android.finnesretrofitmvvm.databinding.ActivityMainBinding
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2.SharedViewModels


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit  var sharedViewModels: SharedViewModels  // by activityViewModels()
    val TAG = "MainActivity "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)//(R.layout.activity_main)

        val mainRepository = MainRepository(UserdataDatabase(this))
        sharedViewModels = ViewModelProvider(this)[SharedViewModels::class.java]

        val viewModelProviderFactory = MainViewModelProviderFactory(
           app = this.application,
            mainRepository = mainRepository,
            context = this
//            getUserNameUseCase,
//            saveUserNameUseCase
        )
        sharedViewModels.endData.observe(this, Observer {
            sharedViewModels.changeStartEndDataAPI()
        })
//        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)
        viewModel = ViewModelProvider(this, MainViewModelProviderFactory(
            context = this,
        app = this.application,
            mainRepository = mainRepository
            ))[MainViewModel::class.java]//ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)
        var recivedIdUserFromSharedPreferences = viewModel.getIdFromSharedPreferenses()
        if (recivedIdUserFromSharedPreferences == 85000){
           viewModel.createDataUser()
            viewModel.query()
            Log.e(TAG, "${viewModel.usersData}")

        }else{
        var recivedUserFromSharedPreferences = viewModel.getUserFromSharedPreferenses()
Log.e(TAG, "${recivedIdUserFromSharedPreferences}")
        if ((recivedIdUserFromSharedPreferences > 0) and (recivedIdUserFromSharedPreferences !=85000)){
            recivedUserFromSharedPreferences.password?.let {
                recivedUserFromSharedPreferences.email?.let { it1 ->
                    viewModel.getQueryUserDataCall(
                        id = recivedIdUserFromSharedPreferences,
                        emailLogin = it1,
                        passwordLogin = it
                    )
                }
            }
        }
//            findNavController().navigate(R.id.action_page2Data1Fragment_to_part2Page1Fragment)
        }
    }





}