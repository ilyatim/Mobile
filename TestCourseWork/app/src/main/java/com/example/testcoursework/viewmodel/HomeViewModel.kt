package com.example.testcoursework.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log

class HomeViewModel : ViewModel()
{
    val countOfDrunkWater = ObservableField<Int>(0)
    val coveredDistance = ObservableField<Float>(0.0f)
    val numberOfSteps = ObservableField<Int>(0)
    val currentWeight = ObservableField<Float>(0.0f)


    fun increaseTheAmountOfWaterDrunk()
    {
        Log.d("increase", "${countOfDrunkWater.get()}")
        countOfDrunkWater.set(countOfDrunkWater.get()?.plus(1))
    }
    fun decreaseTheAmountOfWaterDrunk()
    {
        if(countOfDrunkWater.get()!! > 0)
            countOfDrunkWater.set(countOfDrunkWater.get()?.minus(1))
    }
    // TODO: Implement the ViewModel
}
