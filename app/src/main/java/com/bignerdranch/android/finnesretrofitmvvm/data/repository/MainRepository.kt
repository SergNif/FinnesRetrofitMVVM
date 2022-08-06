package com.bignerdranch.android.finnesretrofitmvvm.data.repository

import com.bignerdranch.android.finnesretrofitmvvm.data.api.RetrofitInstance
import com.bignerdranch.android.finnesretrofitmvvm.data.db.UserdataDatabase
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.UsersData

class MainRepository (
    val db: UserdataDatabase
        ){
//suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
//        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
    suspend fun getBreakingNews(params: UsersData) =
        RetrofitInstance.api.getBreakingNews(params)

//    suspend fun postUserData(params: UsersData) =
//        RetrofitInstance.api.postQueryCreateUser(params)

    fun postUserData(params: UsersData) =
        RetrofitInstance.api.postQueryCreateUser(params)

    suspend fun getDataUse(id: Int, emailLogin: String, passwordLogin: String) =
        RetrofitInstance.api.getForUsersData(id, emailLogin, passwordLogin)

//    suspend fun upsert(article: UsersData) = db.getArticleDao().upsert(article)
//    fun getSavedNews() = db.getArticleDao().getAllUsersDatas()
//    suspend fun deleteArticle(article: UsersData) = db.getArticleDao().deleteUsersData(article)
}
