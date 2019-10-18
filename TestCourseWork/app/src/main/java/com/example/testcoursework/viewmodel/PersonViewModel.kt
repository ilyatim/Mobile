package com.example.testcoursework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import com.example.testcoursework.data.liveData.SingleLiveData
import com.example.testcoursework.ui.activity.MainActivity

class PersonViewModel : ViewModel()
{
    val uiEventLiveData = SingleLiveData<Int>()

    fun onClick(item: Int)
    {
        uiEventLiveData.value = item
    }
    // TODO: Implement the ViewModel
}
