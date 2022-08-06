package com.bignerdranch.android.finnesretrofitmvvm.domain.models.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.data.*
import java.io.Serializable

@Entity(tableName = "users")
data class UsersData constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var userpage: User? = null,
//    val fullName: String? = null,
//    val email: String? = null,
//    val passwordFB: String? = null,
//    val phone: String? = null,
//    val gender: String? = null,
    var datapage1: DataPage1? = null,
    var datapage3: DataPage3? = null,
    var datapage4: DataPage4? = null,
    var datapage5: DataPage5? = null,
    var datapage6: DataPage6? = null,
    var datapage7: DataPage7? = null,
    var datapage8: DataPage8? = null,
    var datapage9: DataPage9? = null,
    var datapage10: DataPage10? = null,

    ): Serializable