package com.bignerdranch.android.finnesretrofitmvvm.data.db


import androidx.room.TypeConverter
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.data.*
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.UserMenuDay
import java.util.*


class Converters {
//    @TypeConverter
//    fun fromSource(source: Source): String? {
//        return source.name
//    }
//    @TypeConverter
//    fun toSource(name: String): Source {
//        return Source(name, name)
//    }

    @TypeConverter
    fun fromUser(us: User): String? {
        return us.toString()
    }

    @TypeConverter
    fun toUser(us: String): User {
        return User(us.toInt())
    }

    @TypeConverter
    fun fromDataPage1(page1: DataPage1): String? {
        return page1.toString()
    }

    @TypeConverter
    fun toDataPage1(page1: String): DataPage1 {
        return DataPage1(page1.toInt(), page1.toBoolean())
    }

//    @TypeConverter
//    fun fromDataPage1(womanMan: DataPage1): String? {
//        return womanMan.gender.toString()
//    }
//
//    @TypeConverter
//    fun toDataPage1(gender: String): DataPage1 {
//        return DataPage1(gender)
//    }

    @TypeConverter
    fun fromDataPage3(page3: DataPage3): String? {
        return page3.toString()
    }

    @TypeConverter
    fun toDataPage3(page3: String): DataPage3 {
        return DataPage3(page3.toInt(),page3.toInt(), page3.toInt(), page3, page3, page3.toString()) //DataPage3(page3)
    }

    @TypeConverter
    fun fromDataPage4(page4: DataPage4): String? {
        return page4.toString()
    }

    @TypeConverter
    fun toDataPage4(page4: String): DataPage4 {
        return DataPage4(page4.toInt(),page4.toBoolean())
    }

    @TypeConverter
    fun fromDataPage5(page5: DataPage5): String? {
        return page5.toString()
    }

    @TypeConverter
    fun toDataPage5(page5: String): DataPage5 {
        return DataPage5(page5.toInt(),page5.toBoolean())
    }

    @TypeConverter
    fun fromDataPage6(page6: DataPage6): String? {
        return page6.toString()
    }

    @TypeConverter
    fun toDataPage6(page6: String): DataPage6 {
        return DataPage6(page6.toInt(),page6.toBoolean())
    }

    @TypeConverter
    fun fromDataPage7(page7: DataPage7): String? {
        return page7.toString()
    }

    @TypeConverter
    fun toDataPage7(page7: String): DataPage7 {
        return DataPage7(page7.toInt(),page7.toBoolean())
    }

    @TypeConverter
    fun fromDataPage8(page8: DataPage8): String? {
        return page8.toString()
    }

    @TypeConverter
    fun toDataPage8(page8: String): DataPage8 {
        return DataPage8(page8.toInt(),page8.toBoolean())
    }

    @TypeConverter
    fun fromDataPage9(page9: DataPage9): String? {
        return page9.toString()
    }

    @TypeConverter
    fun toDataPage9(page9: String): DataPage9 {
        return DataPage9(page9.toInt(),page9.toBoolean())
    }

    @TypeConverter
    fun fromDataPage10(page10: DataPage10): String? {
        return page10.toString()
    }

    @TypeConverter
    fun toDataPage10(page10: String): DataPage10 {
        return DataPage10(page10.toInt(),page10.toBoolean())
    }

    @TypeConverter
    fun fromUserMenuDay(menuDay: UserMenuDay): String? {
        return menuDay.toString()
    }

    @TypeConverter
    fun toUserMenuDay(menuDay: String): UserMenuDay {
        return UserMenuDay(menuDay.toInt(),menuDay.toInt(),menuDay.toInt()
            ,menuDay.toString(),menuDay.toString(),menuDay.toDouble(),menuDay.toInt(),
            menuDay.toDouble(),menuDay.toInt(),menuDay.toString(),menuDay.toString())
    }

//    @TypeConverter
//    fun fromChartWeigh(chartWeigh: ChartWeigh): Double? {
//        return chartWeigh.toString()
//    }
//
//    @TypeConverter
//    fun toChartWeigh(chartWeigh: String): ChartWeigh {
//        return UserMenuDay(chartWeigh.toDouble())
//    }



//    @TypeConverter
//    fun fromDataPage3(value: Date): Long {
//        return value.time
//    }
//
//    @TypeConverter
//    fun toDataPage3(value: Long): Date {
//        return Date(value)
//    }
//
//    @TypeConverter
//    fun fromDataDoubl3(value: Double): Long {
//        return value.toLong()
//    }
//
//    @TypeConverter
//    fun toDataDoubl3(value: Long): Double {
//        return value.toDouble()
//    }


}