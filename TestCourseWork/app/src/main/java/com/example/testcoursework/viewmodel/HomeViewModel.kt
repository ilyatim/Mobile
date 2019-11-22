package com.example.testcoursework.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ReportFragment
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.ui.mFragment.HomeFragment
import com.example.testcoursework.utils.googleAccount.GoogleAccount
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import kotlin.math.round

class HomeViewModel(val app: Application): AndroidViewModel(app)
{
    private val context: Context = app.baseContext

    val countOfDrunkWater = ObservableInt(Singleton.personActivity.countOfDrunkWater.value!!)
    val coveredDistance = ObservableFloat(Singleton.personActivity.coveredDistance.value!!)
    val numberOfSteps = ObservableField(Singleton.personActivity.numberOfSteps.value!!)
    val calories = ObservableField(Singleton.personActivity.calories.value!!)
    val currentWeight = ObservableFloat(Singleton.personActivity.person.value?.weight?.value!!)

    companion object {
        private const val LOG_TAG: String = "ViewModel"
    }
    fun increaseTheAmountOfWaterDrunk()
    {
        Singleton.personActivity.increaseWater()
    }
    fun decreaseTheAmountOfWaterDrunk()
    {
        if(countOfDrunkWater.get() > 0)
        {
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
}
