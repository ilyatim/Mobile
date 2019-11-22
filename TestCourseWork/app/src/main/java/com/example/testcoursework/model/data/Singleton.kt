package com.example.testcoursework.model.data

import androidx.lifecycle.MutableLiveData
import com.example.testcoursework.model.data.dataClass.Person
import com.example.testcoursework.model.data.dataClass.PersonActivity
import com.example.testcoursework.model.dataUtil.DataProcessing

object Singleton
{
    var personActivity: PersonActivity = PersonActivity(
        MutableLiveData(0),     //count of drunk water glass
        MutableLiveData(0f),    //covered distance
        MutableLiveData(0),     //number of steps
        MutableLiveData(0),     //calories
        MutableLiveData(Person(MutableLiveData("Non gender"), MutableLiveData(0), MutableLiveData(0f)))
    )
}