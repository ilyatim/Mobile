package com.example.testcoursework.model.data.dataClass

import android.net.Uri
import androidx.lifecycle.MutableLiveData

data class Person(var gender: MutableLiveData<String>, var height: MutableLiveData<Int>, var weight: MutableLiveData<Int>)
{
    var name: MutableLiveData<String?> = MutableLiveData(null)
    var photoUrl: MutableLiveData<Uri?> = MutableLiveData(null)
}
