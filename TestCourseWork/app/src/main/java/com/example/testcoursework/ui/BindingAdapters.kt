package com.example.testcoursework.ui

import android.content.Intent
import android.databinding.BindingAdapter
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import android.widget.ImageButton
import com.example.testcoursework.data.Singleton

object BindingAdapters
{
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @BindingAdapter("setClipToOutLine")
    @JvmStatic fun setClipToOutLine(imgBtn: ImageButton, boolean: Boolean)
    {
        imgBtn.clipToOutline = boolean
    }
}