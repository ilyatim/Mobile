package com.example.testcoursework.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.testcoursework.BuildConfig

class InformationViewModel: ViewModel()
{
    val appVersion = ObservableField<String>(BuildConfig.VERSION_NAME)
}