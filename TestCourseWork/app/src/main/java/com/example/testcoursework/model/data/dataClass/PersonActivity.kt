package com.example.testcoursework.model.data.dataClass

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PersonActivity {
    val countOfDrunkWater: MutableLiveData<Int> = MutableLiveData(0)
    val coveredDistance: MutableLiveData<Float> = MutableLiveData(0f)
    val numberOfSteps: MutableLiveData<Int> = MutableLiveData(0)
    val calories: MutableLiveData<Int> =MutableLiveData(0)
    fun increaseWater() {
        val value: Int = countOfDrunkWater.value!!
        countOfDrunkWater.value = value + 1
    }
    fun decreaseWater() {
        val value: Int = countOfDrunkWater.value!!
        countOfDrunkWater.value = value - 1
    }
}
