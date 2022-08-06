package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_1
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_1_NO_PRESS
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_4
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_4_NO_PRESS
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_5
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_5_NO_PRESS

class ImageLoader {
    val imageResPage4NoPress = ObservableField(IC_DRAW_4)
    val imageResPage4 = ObservableField(IC_DRAW_4_NO_PRESS)

    val imageResource = ObservableField(IC_DRAW_1)
    val imageResourceNoPress = ObservableField(IC_DRAW_1_NO_PRESS)

    val imageResPage5 = ObservableField(IC_DRAW_5_NO_PRESS)
    val imageResPage5NoPress = ObservableField(IC_DRAW_5)

    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun setImage(imageView: TextView, imageRes: Int) {
            imageView.setBackgroundResource(imageRes)
        }
    }
}