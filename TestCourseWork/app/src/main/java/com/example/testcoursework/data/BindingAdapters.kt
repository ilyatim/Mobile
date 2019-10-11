package com.example.testcoursework.data

import android.databinding.BindingAdapter
import android.util.Log
import android.view.View

object BindingAdapters
{
    fun onClickIncrease()
    {
        Log.d("increase", "da")
        Singleton.personActivity.increaseWater()
    }
}