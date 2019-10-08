package com.example.testcoursework.viewmodel

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.util.Log
import java.util.*

class HomeViewModel : ViewModel()
{
    var countOfDunkWater = ObservableInt(0)
    var coveredDistance = ObservableInt(0)
    var numberOfSteps = ObservableInt(0)

    fun increaseTheAmountOfWaterDrunk()
    {
        Log.d("increase", "$countOfDunkWater")
        countOfDunkWater.set(countOfDunkWater.get() + 1)
    }
    fun decreaseTheAmountOfWaterDrunk()
    {
        if(countOfDunkWater.get() > 0)
            countOfDunkWater.set(countOfDunkWater.get() - 1)
    }
    // TODO: Implement the ViewModel
}
