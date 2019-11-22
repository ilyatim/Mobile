package com.example.testcoursework.model.data.dataClass

import androidx.lifecycle.MutableLiveData

data class Person(var gender: MutableLiveData<String>, var height: MutableLiveData<Int>, var weight: MutableLiveData<Float>)