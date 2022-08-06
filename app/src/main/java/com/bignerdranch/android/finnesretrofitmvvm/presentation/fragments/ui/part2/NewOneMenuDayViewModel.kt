package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.finnesretrofitmvvm.data.api.RetroService
import com.bignerdranch.android.finnesretrofitmvvm.data.api.RetrofitInstance
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.menu.MenuDayList
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.UserMenuDay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParsePosition

class NewOneMenuDayViewModel : ViewModel() {
    val TAG = "Fragment NewOneMenuDayViewModel"
    fun createUserMenuDay(
//        id_tab: Int,
        id: Int,
        age: Int,
        date: String,
        time: String,
        desired_weight: Double,
        height: Int,
        weight: Double,
        fitness_id: Int,
        header: String,
        menu: String,
    ): UserMenuDay {
        var _classUserMenuDay = UserMenuDay(
            0,
            id,
            age,
            date,
            time,
            desired_weight,
            height,
            weight,
            fitness_id,
            header,
            menu)
        return _classUserMenuDay
    }

    // TODO: Implement the ViewModel
    fun launchPostMenuDay(userParam: UserMenuDay, position: Int) = viewModelScope.launch {
        postMenuDay(userParam, position)
    }

    private suspend fun postMenuDay(userParam: UserMenuDay, position: Int) {
        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)

        val call =
            retrofitInstance.postQueryCreateMenuDay(menuDay = userParam, position = position)//, date = java.sql.Date(2022872))
        call.enqueue(object : Callback<UserMenuDay> {
            override fun onFailure(call: Call<UserMenuDay>, t: Throwable) {
                Log.e(TAG, "POST MENU Day NULL")

            }

            override fun onResponse(call: Call<UserMenuDay>, response: Response<UserMenuDay>) {
                if (response.isSuccessful) {
                    Log.e(TAG, "POST MENU Day IS ${response.body()}")

                } else {
                    Log.e(TAG, "POST MENU Day ELSE ${response.body()}")

                }
            }
        }
        )
    }

    fun launchDeleteOneMenuDay(userId:Int, position: Int?) = viewModelScope.launch {
        val vde = "fgr"
        deleteOneMenuDay(userId, position)
    }

    private fun deleteOneMenuDay(userId: Int, pos: Int?) {
        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)

        pos?.let { retrofitInstance.deleteOneMenuDay(user_id = userId, position = it) }
            ?.enqueue(object : Callback<MenuDayList> {
                override fun onResponse(call: Call<MenuDayList>, response: Response<MenuDayList>) {
                    if (response.isSuccessful) {
                        Log.e(TAG, "POST MENU Day IS ${response.body()}")
                    } else {
                        Log.e(TAG, "POST MENU Day ELSE ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<MenuDayList>, t: Throwable) {
                    Log.e(TAG, "POST MENU Day NULL")
                }
            }
            )
//        http://195.234.208.168:8085/fit_delete_one_day_menu/24/12
//        http://195.234.208.168:8085/fit_delete_one_day_menu/24/12/
    }


}