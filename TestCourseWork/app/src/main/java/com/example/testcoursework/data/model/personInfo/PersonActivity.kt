package com.example.testcoursework.data.model.personInfo

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
