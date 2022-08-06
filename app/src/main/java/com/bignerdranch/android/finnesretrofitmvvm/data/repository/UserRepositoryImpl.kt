package com.bignerdranch.android.finnesretrofitmvvm.data.repository

import com.bignerdranch.android.finnesretrofitmvvm.data.storage.SharedPrefUserStorage
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.SaveUserNameParam
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User
import com.bignerdranch.android.finnesretrofitmvvm.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: SharedPrefUserStorage) : UserRepository {

    override fun saveIdFromApi(saveParam: Int): Boolean {
//        val user = User(firstName = saveParam.name, lastName = "")
//        val user = mapToStorage(saveParam)
        val result = userStorage.saveId(saveParam)  // (user)
        return result
    }

    override fun saveUser(saveParam: SaveUserNameParam): Boolean {
//        val user = User(firstName = saveParam.name, lastName = "")
        val user = mapToStorage(saveParam)
        val result = userStorage.save(user)
        return result
    }

    override fun getIdFromApi(): Int {
        val  result = userStorage.getId()
        return result
    }

    override fun getStartEndData(): List<String> {
        val  result = userStorage.getData()
        return result
    }

    override fun getName(): User {
        val user = userStorage.get()
//        val userName = User(firstName = user.firstName, lastName = user.lastName)
        return mapToDomain(user) //userName
    }

    override fun saveDataStartDataCalendar(startData: String, endData: String): Boolean {
        val result = userStorage.saveData(startData = startData, endData = endData)
        return result
    }

    private fun mapToStorage(saveParam: SaveUserNameParam): User {
        return User(
            id = saveParam.id,
            fullName = saveParam.fullName,
            email = saveParam.email,
            password = saveParam.passwordFB)
    }

    private fun mapToDomain(user: User): User {
        return User(id = user.id, fullName = user.fullName, email = user.email, password = user.password)//, fitness_id = user.fitness_id)
    }

}