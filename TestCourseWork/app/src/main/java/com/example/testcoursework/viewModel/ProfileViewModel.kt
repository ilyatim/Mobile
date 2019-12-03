package com.example.testcoursework.viewModel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.example.testcoursework.model.data.Singleton

class ProfileViewModel : ViewModel()
{
    companion object {
        const val LOG_TAG: String = "ProfileViewModel"
    }
    val gender = ObservableField<String>(Singleton.person.gender.value)
    val weight = ObservableInt(Singleton.person.weight.value!!)
    val height = ObservableInt(Singleton.person.height.value!!)
}