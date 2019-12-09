package com.example.testcoursework.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class WorkoutViewModel : ViewModel() {
    val visibility = ObservableField<Boolean>(false)
}
