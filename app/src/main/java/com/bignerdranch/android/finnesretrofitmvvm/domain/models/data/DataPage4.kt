package com.bignerdranch.android.finnesretrofitmvvm.domain.models.data


import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

data class DataPage4 (

//    val dataPage4: String = "minimalPhysicalActive",
//    var minimalPhysicalActive:String="minimalPhysicalActive",
//    var fastWalkOnFoot:String="fastWalkOnFoot",
//    var examine1_2TimesWeek:String="examine1_2TimesWeek",
//    var examine3_5TimesWeek:String="examine3_5TimesWeek",
//    var everyDayFitness:String="everyDayFitness",
//    val date: LocalDate = LocalDate.parse("2018-12-31"),
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var minimalPhysicalActive:Boolean=true,
    var fastWalkOnFoot:Boolean=false,
    var examine1_2TimesWeek:Boolean=false,
    var examine3_5TimesWeek:Boolean=false,
    var everyDayFitness:Boolean=false,
    var date:String = LocalDateTime.now().toString().split("T")[0],
    var fitness_id:Int? = null,
)
{
    init {
        if(minimalPhysicalActive) {
            fastWalkOnFoot=false
            examine1_2TimesWeek=false
            examine3_5TimesWeek=false
            everyDayFitness=false
        }
        if(fastWalkOnFoot) {
            minimalPhysicalActive=false
            examine1_2TimesWeek=false
            examine3_5TimesWeek=false
            everyDayFitness=false
        }

        if(examine1_2TimesWeek) {
            minimalPhysicalActive=false
            fastWalkOnFoot=false
            examine3_5TimesWeek=false
            everyDayFitness=false
        }

        if(examine3_5TimesWeek) {
            minimalPhysicalActive=false
            fastWalkOnFoot=false
            examine1_2TimesWeek=false
            everyDayFitness=false
        }

        if(everyDayFitness) {
            minimalPhysicalActive=false
            fastWalkOnFoot=false
            examine1_2TimesWeek=false
            examine3_5TimesWeek=false
        }
    }
}
