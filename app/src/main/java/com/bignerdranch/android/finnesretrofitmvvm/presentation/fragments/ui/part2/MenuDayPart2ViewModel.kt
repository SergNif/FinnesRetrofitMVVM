package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.bignerdranch.android.finnesretrofitmvvm.data.api.RetroService
import com.bignerdranch.android.finnesretrofitmvvm.data.api.RetrofitInstance
import com.bignerdranch.android.finnesretrofitmvvm.data.repository.UserRepositoryImpl
import com.bignerdranch.android.finnesretrofitmvvm.data.storage.SharedPrefUserStorage
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.menu.MenuDayList
import com.bignerdranch.android.finnesretrofitmvvm.domain.usecase.GetUserNameUseCase
import com.bignerdranch.android.finnesretrofitmvvm.domain.usecase.SaveUserNameUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat


class MenuDayPart2ViewModel(application: Application) : AndroidViewModel(application){

    var dateMenuRecyle: Long = 0//LocalDateTime.now().plusDays(1).toString().split("T")[0]
private val context = getApplication<Application>().applicationContext
    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = context))
    }
    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(userRepository = userRepository)
    }
    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(userRepository = userRepository)
    }
    lateinit var recyclerListData: MutableLiveData<MenuDayList>
    val TAG = "MenuDayPart2ViewModel Part2 Menu Day Recycler Day"
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    init {
        recyclerListData = MutableLiveData<MenuDayList>()
    }
    fun getMenuListObserverable():MutableLiveData<MenuDayList>{
        return recyclerListData
    }


    fun launchGetMenuList(startData: String, endData: String) = viewModelScope.launch {
        delay(500)
        getMenuList(startData, endData)
    }

    fun getIdFromSharedPreferenses(): Int {
        val resultGetIdData: Int = saveUserNameUseCase.executeGet()
//        resultLiveMutable.value = "Get id = resultGetIdData"
        Log.e(TAG, "getIdFromApi viewModel $resultGetIdData")
        return resultGetIdData
    }



    private fun  getMenuList(startData: String, endData: String) {
        Log.e(TAG, " API query startData ${startData}  endData ${endData}")
        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
//        val call = retrofitInstance.getMenuStrings(id = getIdFromSharedPreferenses(), date = LocalDateTime.now().plusDays(dateMenuRecyle).toString().split("T")[0])
        val call = retrofitInstance.getMenuStrings(id = getIdFromSharedPreferenses(), startDate = startData, endDate = endData)
        call.enqueue(object : Callback<MenuDayList>{
            override fun onFailure(call: Call<MenuDayList>, t: Throwable) {
                Log.e(TAG, "Get Menu List NULL")
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<MenuDayList>, response: Response<MenuDayList>) {
                if (response.isSuccessful){
                    Log.e(TAG, "Get Menu List IS ${response.body()}")
                    recyclerListData.postValue(response.body())

                }else {
                    Log.e(TAG, "Get Menu List ELSE ${response.body()}")
                    recyclerListData.postValue(null)
                }
            }
        }
        )
    }

    fun launchSearchMenuDay (f:String) = viewModelScope.launch {
        searchMenuList(f)
    }

    private fun  searchMenuList(searchMenuList:String){
        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
        val call = retrofitInstance.searchMenues(searchMenuList)//   getMenuStrings(id = 3, date = Date(2022872))
        call.enqueue(object : Callback<MenuDayList>{
            override fun onFailure(call: Call<MenuDayList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<MenuDayList>, response: Response<MenuDayList>) {
                if (response.isSuccessful){
                    recyclerListData.postValue(response.body())
                }else {
                    recyclerListData.postValue(null)
                }
            }
        }
        )
    }
}