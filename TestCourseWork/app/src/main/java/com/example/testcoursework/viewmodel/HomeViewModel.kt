package com.example.testcoursework.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import android.util.Log
import com.example.testcoursework.data.Singleton
import org.koin.core.context.GlobalContext.get
import org.koin.core.parameter.parametersOf

class HomeViewModel: ViewModel()
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
