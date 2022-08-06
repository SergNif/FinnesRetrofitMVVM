package com.bignerdranch.android.finnesretrofitmvvm.domain.repository

import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.SaveUserNameParam
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User

interface UserRepository {

    fun saveIdFromApi(saveParam: Int): Boolean
    fun saveUser(saveParam: SaveUserNameParam): Boolean
    fun getIdFromApi(): Int
    fun getStartEndData(): List<String>
    fun getName(): User
    fun saveDataStartDataCalendar(startData: String, endData: String): Boolean
}