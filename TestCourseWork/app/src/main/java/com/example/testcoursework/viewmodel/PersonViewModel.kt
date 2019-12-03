package com.example.testcoursework.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.model.data.liveData.SingleLiveData

class PersonViewModel : ViewModel()
{
    val email = ObservableField(Singleton.person.email.value)
    val fullName = ObservableField(Singleton.person.fullName.value)
    val uiEventLiveData = SingleLiveData<Int>()

    fun onClick(item: Int)
    {
        uiEventLiveData.value = item
    }

    // TODO: Implement the ViewModel
}
