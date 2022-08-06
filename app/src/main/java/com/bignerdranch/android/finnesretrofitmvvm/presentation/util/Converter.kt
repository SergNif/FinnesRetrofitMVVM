package com.bignerdranch.android.finnesretrofitmvvm.presentation.util

import androidx.databinding.InverseMethod
import java.text.DecimalFormat

object Converter {

    @JvmStatic
    @InverseMethod("stringToDouble")
    fun doubleToString(value: Double?): String? {

        if (value == null) {
            return null
        }
        return DecimalFormat("###.##").format(value)
    }

    @JvmStatic
    fun stringToDouble(value: String?): Double? {

        if (value == null) {
            return null
        }
        val v = DecimalFormat("###.##").parse(value)
        return v.toDouble()
    }
}