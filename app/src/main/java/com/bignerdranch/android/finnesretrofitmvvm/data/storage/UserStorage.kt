package com.bignerdranch.android.finnesretrofitmvvm.data.storage

import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User

interface UserStorage {
    fun save(user: User): Boolean
    fun get(): User
    fun saveId(id: Int):Boolean
    fun getId(): Int
    fun saveData(startData: String, endData: String): Boolean
    fun getData(): List<String>
}