package com.bignerdranch.android.finnesretrofitmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.UsersData


@Dao
interface UsersDataDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun upsert(article: UsersData): Long
//
//    @Query("SELECT * FROM users")
//    fun getAllUsersDatas(): LiveData<List<UsersData>>
//
//    @Delete
//    suspend fun deleteUsersData(article: UsersData)
}