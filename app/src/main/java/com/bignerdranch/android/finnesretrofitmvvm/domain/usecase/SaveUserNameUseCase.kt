package com.bignerdranch.android.finnesretrofitmvvm.domain.usecase

import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.SaveUserNameParam
import com.bignerdranch.android.finnesretrofitmvvm.domain.repository.UserRepository

class SaveUserNameUseCase (private val userRepository: UserRepository){
    fun executeSave(param: Int): Boolean {

        val result: Boolean = userRepository.saveIdFromApi(saveParam = param)
        return result
    }
    fun executeGet(): Int {

        val result: Int = userRepository.getIdFromApi()
        return result
    }

    fun executeSave(param: SaveUserNameParam): Boolean {

        val result: Boolean = userRepository.saveUser(saveParam = param)
        return result
    }

    fun executeSaveData(startData: String ="", endData:String =""):Boolean{
        val result: Boolean = userRepository.saveDataStartDataCalendar(startData = startData, endData = endData)
        return result
    }
}