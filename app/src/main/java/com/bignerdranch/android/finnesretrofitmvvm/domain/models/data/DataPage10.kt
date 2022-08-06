package com.bignerdranch.android.finnesretrofitmvvm.domain.models.data


import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

data class DataPage10(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var fastFood:Boolean=true,
    var laterNight:Boolean=false,
    var fastSugar:Boolean=false,
    var Nothing:Boolean=false,
    var date: String = LocalDateTime.now().toString().split("T")[0],
    var fitness_id:Int? = null,
)

{
//    init {
//        if (fastFood)
//        {
//            laterNight=false
//            fastSugar=false
//            Nothing=false
//        }
//
//        if (laterNight)
//        {
//            fastFood=false
//            fastSugar=false
//            Nothing=false
//        }
//
//        if (fastSugar)
//        {
//            laterNight=false
//            fastFood=false
//            Nothing=false
//        }
//
//        if (Nothing)
//        {
//            laterNight=false
//            fastFood=false
//            fastSugar=false
//        }
//
//    }
}
