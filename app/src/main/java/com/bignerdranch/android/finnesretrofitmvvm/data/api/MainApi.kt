package com.bignerdranch.android.finnesretrofitmvvm.data.api



import com.bignerdranch.android.finnesretrofitmvvm.domain.models.data.DataPage3
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.menu.MenuDayList
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.UsersData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.sql.Date

interface MainApi {

    //    @Multipart
    @POST("fit_new_user/")
//        @FormUrlEncoded
    fun createUser(
//        @Part("id") id: Int,
//        @Part("bod") params: UsersData
        @Body params: UsersData
    ): Call<UsersData>

    @PATCH("/fit_update_user/{user_id}")
//        @FormUrlEncoded
    fun updateUser(
        @Path("user_id") user_id: Int,
//        @Query("email") email: String,
//        @Query("password") password: String,
//        @Query("fullName") fullName: String,

        @Body params: UsersData
    ): Call<UsersData>


    @POST("fit_new_user/")
    fun postQueryCreateUser(
        @Body params: UsersData
    ): Response<UsersData>

    @GET("top....")
    suspend fun getBreakingNews(
        @Body params: UsersData
//            @Query("country")
//            countryCode: String = "us",
//            @Query("page")
//            pageNumber: Int = 1,
//            @Query("apiKey")
//            apikey: String = API_KEY
    ): Response<UsersData>

    @GET("v2/everything")
    suspend fun searchForNews_old(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: String = "1",
//            @Query("apiKey")
//            apikey: String = API_KEY
    ): Response<UsersData>

    @GET("fit_get_one_user/")
    suspend fun getForUsersData(
        @Query("idQuery") id: Int,
        @Query("emailQuery")
        emailQuery: String,
        @Query("passwQuery")
        passwQuery: String,
    ): Response<UsersData>

    @GET("fit_get_menu_string/")
    suspend fun getMenuStrings(
        @Query("userMenuQiery") id: Int,
        @Query("dataMenu") date: Date
    ): Response<MenuDayList>

    @POST("fit_new_menu_day/")
    suspend fun postQueryCreateMenuDay(
        @Body user: UsersData,
        @Body date: Date
    ): Response<MenuDayList>

    @PATCH("fit_update_menu_string_day/{user_id}")
    suspend fun updateMenuDayString(
        @Path("user_id") user: UsersData,
        @Body date: Date,
    ): Response<MenuDayList>

    @PATCH("fit_update/{user_id}")
    suspend fun updateHaigthWeigthAge(
        @Path("user_id") user_id: Int,
        @Query("userData") userData: DataPage3,
//        @Query("data") date: Date,
    ): Response<DataPage3>

    @DELETE("fit_update_menu_string_day/{user_id}")
    suspend fun deleteMenuDayString(
        @Path("user_id") user: UsersData,
//        @Body date: Date,
    ): Response<MenuDayList>


}


