package com.example.testcoursework.viewModel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.example.testcoursework.model.data.Singleton

class HomeViewModel: ViewModel()
{
    val countOfDrunkWater = ObservableInt(Singleton.personActivity.countOfDrunkWater.value!!)
    val coveredDistance = ObservableFloat(Singleton.personActivity.coveredDistance.value!!)
    val numberOfSteps = ObservableField(Singleton.personActivity.numberOfSteps.value!!)
    val calories = ObservableField(Singleton.personActivity.calories.value!!)
    val currentWeight = ObservableInt(Singleton.person.weight.value!!)
    val name = ObservableField(Singleton.person.name.value)
    val photoUrl = ObservableField(Singleton.person.photoUrl.value)

    companion object {
        private const val LOG_TAG: String = "ViewModel"
    }
    fun increaseTheAmountOfWaterDrunk()
    {
        Singleton.personActivity.increaseWater()
    }
}
