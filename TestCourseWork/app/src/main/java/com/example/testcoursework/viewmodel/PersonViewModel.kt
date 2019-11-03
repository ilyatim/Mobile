package com.example.testcoursework.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testcoursework.model.data.liveData.SingleLiveData

class PersonViewModel : ViewModel()
{
    val uiEventLiveData = SingleLiveData<Int>()

    fun onClick(item: Int)
    {
        uiEventLiveData.value = item
    }
    // TODO: Implement the ViewModel
}
