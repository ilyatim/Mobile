package com.example.testcoursework.model.data.dataClass

import android.net.Uri
import androidx.lifecycle.MutableLiveData

class Person
{
    var gender: MutableLiveData<String> = MutableLiveData("Неопределенно")
    var height: MutableLiveData<Int> = MutableLiveData(0)
    var weight: MutableLiveData<Int> = MutableLiveData(0)
    var name: MutableLiveData<String?> = MutableLiveData(null)
    val fullName: MutableLiveData<String?> = MutableLiveData(null)
    var photoUrl: MutableLiveData<Uri?> = MutableLiveData(null)
    val email: MutableLiveData<String?> = MutableLiveData(null)
}
