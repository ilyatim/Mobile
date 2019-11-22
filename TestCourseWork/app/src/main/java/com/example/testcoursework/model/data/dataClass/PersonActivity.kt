package com.example.testcoursework.model.data.dataClass

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class PersonActivity(var countOfDrunkWater: MutableLiveData<Int>,
                          var coveredDistance: MutableLiveData<Float>,
                          var numberOfSteps: MutableLiveData<Int>,
                          var calories: MutableLiveData<Int>,
                          val person: MutableLiveData<Person>)
{
    fun increaseWater()
    {
        val value: Int = countOfDrunkWater.value!!
        countOfDrunkWater.value = value + 1
    }
    fun decreaseWater()
    {
        val value: Int = countOfDrunkWater.value!!
        countOfDrunkWater.value = value - 1
    }
}
