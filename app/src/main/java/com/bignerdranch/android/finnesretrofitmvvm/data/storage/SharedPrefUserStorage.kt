package com.bignerdranch.android.finnesretrofitmvvm.data.storage

import android.content.Context
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User
import java.time.LocalDateTime

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_NAME = "nameRepoSerg"
private const val KEY_EMAIL = "Repository Impl"
private const val KEY_PASSWORD = "Default last name"
private const val KEY_STARTDATA = "Start Data name"
private const val KEY_ENDDATA = "End Data name"
private const val DEFAULT_NAME = "Default first name"
private const val DEFAULT_EMAIL = "qq@qq.qq"
private const val DEFAULT_PASSWORD = "123456"

class SharedPrefUserStorage(context: Context) : UserStorage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(user: User): Boolean {
        sharedPreferences.edit().putInt("ID", user.id!!).apply()
        sharedPreferences.edit().putString(KEY_NAME, user.fullName).apply()
        sharedPreferences.edit().putString(KEY_EMAIL, user.email).apply()
        sharedPreferences.edit().putString(KEY_PASSWORD, user.password).apply()
        return true
    }

    override fun get(): User {
        val fullname = sharedPreferences.getString(KEY_NAME, DEFAULT_NAME) ?: "no"
        val email = sharedPreferences.getString(KEY_EMAIL, DEFAULT_EMAIL) ?: "no"
        val passwordFB = sharedPreferences.getString(KEY_PASSWORD, DEFAULT_PASSWORD) ?: "no"
        return User(fullName = fullname, email = email, password = passwordFB)
    }

    override fun saveId(id: Int): Boolean {
        sharedPreferences.edit().putInt("ID", id).apply()
        return true
    }

    override fun getId(): Int {
        val id = 85000
        val loadId = sharedPreferences.getInt("ID", id)
        return loadId
    }

    override fun saveData(startData: String, endData: String): Boolean {
        sharedPreferences.edit().putString(KEY_STARTDATA, startData).apply()
        sharedPreferences.edit().putString(KEY_ENDDATA, endData).apply()
        return true
    }

    override fun getData(): List<String> {
        val startData = sharedPreferences.getString(KEY_STARTDATA,
            LocalDateTime.now().plusDays(0).toString().split("T")[0]) ?: "no"
        val endData = sharedPreferences.getString(KEY_ENDDATA,
            LocalDateTime.now().plusDays(1).toString().split("T")[0]) ?: "no"
        return listOf<String>(startData, endData)
    }

}