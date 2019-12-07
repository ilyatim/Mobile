package com.example.testcoursework.viewModel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.model.data.liveData.SingleLiveData

class PersonViewModel : ViewModel()
{
    val email = ObservableField(Singleton.person.email.value)
    val fullName = ObservableField(Singleton.person.fullName.value)
    val gender = ObservableField<String>(Singleton.person.gender.value?.value)
    val weight = ObservableInt(Singleton.person.weight.value!!)
    val height = ObservableInt(Singleton.person.height.value!!)
    val uiEventLiveData = SingleLiveData<Int>()

    fun onClick(item: Int)
    {
        uiEventLiveData.value = item
    }

}
