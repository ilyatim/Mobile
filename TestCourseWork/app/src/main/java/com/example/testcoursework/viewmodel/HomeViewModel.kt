package com.example.testcoursework.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.utils.googleAccount.GoogleAccount
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field

class HomeViewModel(val app: Application): AndroidViewModel(app)
{
    val countOfDrunkWater = ObservableInt(Singleton.personActivity.countOfDrunkWater)
    val coveredDistance = ObservableFloat(Singleton.personActivity.coveredDistance)
    val numberOfSteps = ObservableField(Singleton.personActivity.numberOfSteps)
    val calories = ObservableField(Singleton.personActivity.calories)
    val currentWeight = ObservableFloat(Singleton.personActivity.currentWeight)

    companion object {
        private const val LOG_TAG: String = "ViewModel"
    }

    private var googleSignInAccount: GoogleSignInAccount? = null

    init {
        googleSignInAccount = GoogleAccount.getLastSignInAccount(app.baseContext)
    }


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

    fun getStepCount()
    {
        googleSignInAccount?.let { it ->
            app.baseContext.let { it1 ->
                Fitness.getHistoryClient(it1, it)
                    .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                    .addOnSuccessListener {
                        numberOfSteps.set(if(it.isEmpty){0} else { it.dataPoints[0].getValue(Field.FIELD_STEPS).asInt() })
                    }
                    .addOnCompleteListener {
                        return@addOnCompleteListener
                    }
                    .addOnFailureListener {
                        Log.d(LOG_TAG, "failure - ${it.message}")
                    }
            }
        }
    }
    fun getCoveredDistance()
    {
        googleSignInAccount?.let { it ->
            app.baseContext.let { it1 ->
                Fitness.getHistoryClient(it1, it)
                    .readDailyTotal(DataType.AGGREGATE_DISTANCE_DELTA)
                    .addOnSuccessListener {
                        val distance: Int = if(it.isEmpty){0} else { it.dataPoints[0].getValue(Field.FIELD_DISTANCE).asFloat().toInt() }
                        Log.d(LOG_TAG, "$distance")
                        coveredDistance.set((distance / 1000).toFloat())
                    }
                    .addOnCompleteListener {
                        return@addOnCompleteListener
                    }
                    .addOnFailureListener {
                        Log.d(LOG_TAG, "failure - ${it.message}")
                    }
            }
        }
    }
    fun getCalories()
    {
        googleSignInAccount?.let { it ->
            app.baseContext.let { it1 ->
                Fitness.getHistoryClient(it1, it)
                    .readDailyTotal(DataType.AGGREGATE_CALORIES_EXPENDED)
                    .addOnSuccessListener {
                        calories.set(if(it.isEmpty){0} else { it.dataPoints[0].getValue(Field.FIELD_CALORIES).asInt() })
                    }
                    .addOnCompleteListener {
                        return@addOnCompleteListener
                    }
                    .addOnFailureListener {
                        Log.d(LOG_TAG, "failure - ${it.message}")
                    }
            }
        }
    }
    // TODO: Implement the ViewModel
}
