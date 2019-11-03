package com.example.testcoursework.ui

import androidx.databinding.BindingAdapter
import android.os.Build
import androidx.annotation.RequiresApi
import android.view.View

object BindingAdapters
{
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @BindingAdapter("setClipToOutLine")
    @JvmStatic fun setClipToOutLine(imgBtn: View, boolean: Boolean)
    {
        imgBtn.clipToOutline = boolean
    }
}