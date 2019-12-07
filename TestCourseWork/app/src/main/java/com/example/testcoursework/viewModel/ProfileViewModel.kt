package com.example.testcoursework.viewModel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.example.testcoursework.model.data.Singleton

class ProfileViewModel : ViewModel()
{
    val gender = ObservableField<String>(Singleton.person.gender.value?.value)
    val weight = ObservableInt(Singleton.person.weight.value!!)
    val height = ObservableInt(Singleton.person.height.value!!)
}