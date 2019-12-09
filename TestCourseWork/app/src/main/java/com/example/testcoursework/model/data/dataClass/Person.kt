package com.example.testcoursework.model.data.dataClass

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.testcoursework.model.data.enumClass.Gender

class Person {
    var gender: MutableLiveData<Gender> = MutableLiveData(Gender.OTHER)
    var height: MutableLiveData<Int> = MutableLiveData<Int>(0)
    var weight: MutableLiveData<Int> = MutableLiveData<Int>(0)
    var name: MutableLiveData<String?> = MutableLiveData(null)
    val fullName: MutableLiveData<String?> = MutableLiveData(null)
    var photoUrl: MutableLiveData<Uri?> = MutableLiveData(null)
    val email: MutableLiveData<String?> = MutableLiveData(null)
}

