package com.bignerdranch.android.finnesretrofitmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.UsersData

@Database(
    entities = [UsersData::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class UserdataDatabase : RoomDatabase(){
    abstract fun getArticleDao(): UsersDataDao
    companion object{
        @Volatile
        private var instanse: UserdataDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instanse ?: synchronized(LOCK){
            instanse ?: createDatabase(context).also { instanse = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserdataDatabase::class.java,
                "user_fitness_db.db"
            ).build()

    }
}