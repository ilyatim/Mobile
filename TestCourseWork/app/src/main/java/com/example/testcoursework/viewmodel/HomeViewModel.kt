package com.example.testcoursework.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableFloat
import android.databinding.ObservableInt
import android.util.Log
import com.example.testcoursework.data.Singleton

class HomeViewModel(app: Application): AndroidViewModel(app)
{
    val countOfDrunkWater = ObservableInt(Singleton.personActivity.countOfDrunkWater)
    val coveredDistance = ObservableFloat(Singleton.personActivity.coveredDistance)
    val numberOfSteps = ObservableInt(Singleton.personActivity.numberOfSteps)
    val currentWeight = ObservableFloat(Singleton.personActivity.currentWeight)


    fun increaseTheAmountOfWaterDrunk()
    {
        countOfDrunkWater.increment()
        Singleton.personActivity.increaseWater()
    }
    fun decreaseTheAmountOfWaterDrunk()
    {
        if(countOfDrunkWater.get() > 0)
        {
            countOfDrunkWater.decrement()
            Singleton.personActivity.decreaseWater()
        }
    }
    private fun ObservableInt.increment()
    {
        set(get() + 1)
    }
    private fun ObservableInt.decrement()
    {
        set(get() - 1)
    }
    // TODO: Implement the ViewModel
}
