package com.bignerdranch.android.finnesretrofitmvvm.data.api

import com.bignerdranch.android.finnesretrofitmvvm.domain.models.data.DataPage3

import com.bignerdranch.android.finnesretrofitmvvm.domain.models.menu.MenuDayList
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.UserMenuDay
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.UsersData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.sql.Date

interface RetroService {

    @GET("fit_get_menu_string/")
    fun getMenuStrings(
        @Query("userMenuQiery") id: Int,
        @Query("startDataMenu") startDate: String,
        @Query("endDataMenu") endDate: String,

    ): Call<MenuDayList>

    @GET("fit_get_one_user_email/")
    fun getUserOnEmail(
        @Query("emailQuery") emailQuery: String,
        @Query("passwQuery") passwQuery: String,

//        'http://195.234.208.168:8085/fit_get_one_user_email/?emailQuery=x93c%40x9c.xx&passwQuery=123456'
    ): Call<User>


    @GET("menues")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun searchMenues(@Query("user") searchText: String): Call<MenuDayList>

    @POST("fit_new_menu_day/{position}/")
    fun postQueryCreateMenuDay(
        @Path("position") position:Int,
//        @Body user: UsersData,
        @Body menuDay: UserMenuDay,

    ): Call<UserMenuDay>



    @PATCH("fit_update/{user_id}/")
    fun updateHigthWeigthAge(
        @Path("user_id") user_id: Int,
        @Body userData3: DataPage3,
//        @Query("data") date: Date,
    ): Call<UsersData>


    @PATCH("fit_update_to_user/{user_id}/")
    fun updateNamePassword(
        @Path("user_id") user_id: Int,
        @Body userName: User,
//        @Query("data") date: Date,
    ): Call<User>


    @PATCH("/fit_update_user/{user_id}")
//        @FormUrlEncoded
    fun updateUser(
        @Path("user_id") user_id: Int,
//        @Part("bod") params: UsersData
        @Body params: UsersData
    ): Call<UsersData>


    @PATCH("fit_update_menu_string_day/{user_id}")
    suspend fun updateMenuDayString(
//        @Path("user_id") user: UsersData,
        @Body date: Date,
    ): Response<MenuDayList>

    @DELETE("fit_update_menu_string_day/{user_id}")
    suspend fun deleteMenuDayString(
//        @Path("user_id") user: UsersData,
        @Body date: Date,
    ): Response<MenuDayList>

    @DELETE("fit_delete_one_day_menu/{user_id}/{position}/")
    fun deleteOneMenuDay(
        @Path("user_id") user_id:Int,
        @Path("position") position:Int,
    ): Call<MenuDayList>

}