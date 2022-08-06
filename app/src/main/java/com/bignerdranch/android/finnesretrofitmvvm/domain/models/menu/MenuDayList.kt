package com.bignerdranch.android.finnesretrofitmvvm.domain.models.menu

import androidx.room.PrimaryKey
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.UsersData
import java.sql.Date

data class MenuDayList(val listMenuDay: List<MenuDay> )

data class MenuDay(
    var id_note:Int,
    var user: String,
    var menu: List<String>,
    var data: String,
    var weight: Double,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0// = user.id
}

//data class ExamplItem(val im:String, val text1:String, val text2:String)
data class DataModel(val title:String?)