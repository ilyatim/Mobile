package com.example.testcoursework.ui

import androidx.databinding.BindingAdapter
import android.os.Build
import androidx.annotation.RequiresApi
import android.view.View
import android.widget.NumberPicker

object BindingAdapters {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @BindingAdapter("setClipToOutLine")
    @JvmStatic fun setClipToOutLine(imgBtn: View, boolean: Boolean) {
        imgBtn.clipToOutline = boolean
    }
    @BindingAdapter("setMinValue")
    @JvmStatic fun setMinValue(numberPicker: NumberPicker, value: Int) {
        numberPicker.minValue = value
    }
    @BindingAdapter("setMaxValue")
    @JvmStatic fun setMaxValue(numberPicker: NumberPicker, value: Int) {
        numberPicker.maxValue = value
    }
}