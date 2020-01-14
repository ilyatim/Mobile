package com.example.testcoursework.data.repo.googleAccount

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.testcoursework.data.model.personInfo.Singleton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.*
import kotlin.math.round

object GoogleAccount {
    private const val LOG_TAG: String = "GoogleData"
    private var instance: GoogleSignInAccount? = null

    fun getLastSignInAccount(context: Context?): GoogleSignInAccount? {
        if (instance == null) {
            instance = GoogleSignIn.getLastSignedInAccount(context)
        }
        return instance
    }
    fun getPersonActivity(context: Context) {
        getStepCount(context)
        getCoveredDistance(context)
        getCalories(context)
    }
    fun retry(context: Context) {
        getPersonActivity(context)
    }
    fun getPersonInfo() {
        Singleton.person.name.value = getName()
        Singleton.person.photoUrl.value = getPhotoUrl()
        Singleton.person.email.value = getEmail()
        Singleton.person.fullName.value = getFullName()
    }
    private fun setDrunkWaterGlasses(context: Context) {
        val dataPoint = createDataSet(DataType.TYPE_HEIGHT, 100, context)
        Log.d(LOG_TAG, dataPoint.toString())
        /*instance?.let {it ->
            context.let { it1 ->
                FitnessBuilder.getHistoryClient(it1, it)
                    .insertData()
            }
        }*/
    }
    private fun createDataSet(dataType: DataType, value: Any, context: Context): DataPoint {
        val dataSource = DataSource.Builder()
            .setAppPackageName(context)
            .setDataType(dataType)
            .build()
        var dataPoint = DataPoint.builder(dataSource)
        when (value) {
            is Int   -> {
                dataPoint = DataPoint.builder(dataSource).setIntValues(value)
            }
            is Float -> {
                dataPoint = DataPoint.builder(dataSource).setFloatValues(value)
            }
        }
        return dataPoint.build()
    }
    private fun getStepCount(context: Context) {
        instance?.let { it ->
            context.let { it1 ->
                Fitness.getHistoryClient(it1, it)
                    .readDailyTotalFromLocalDevice(DataType.TYPE_STEP_COUNT_DELTA)
                    .addOnSuccessListener {
                        Singleton.personActivity.numberOfSteps.value = if (it.dataPoints.isEmpty()){
                            0
                        } else {
                            it.dataPoints[0].getValue(Field.FIELD_STEPS).asInt()
                        }
                    }
                    .addOnCompleteListener {
                        return@addOnCompleteListener
                    }
                    .addOnFailureListener {
                        Log.d(LOG_TAG, "failure in getStepCount - ${it.message}")
                    }
            }
        }
    }
    private fun getCoveredDistance(context: Context) {
        instance?.let { it ->
            context.let { it1 ->
                Fitness.getHistoryClient(it1, it)
                    .readDailyTotalFromLocalDevice(DataType.AGGREGATE_DISTANCE_DELTA)
                    .addOnSuccessListener {
                        val distanceAsFloat: Float
                        val distanceAsInt = if (it.dataPoints.isEmpty()){
                            0f
                        } else {
                            it.dataPoints[0].getValue(Field.FIELD_DISTANCE).asFloat()
                        }.toInt()

                        distanceAsFloat = distanceAsInt.toFloat() / 1000
                        //TODO сделать корректное отображение для < 1000 м.
                        /*when(distanceAsInt) {
                            in 11..100  -> {

                            }
                            in 101..999 -> {
                            }
                        }*/
                        Singleton.personActivity.coveredDistance.value = distanceAsFloat
                    }
                    .addOnCompleteListener {
                        return@addOnCompleteListener
                    }
                    .addOnFailureListener {
                        Log.d(LOG_TAG, "failure in getCoveredDistance - ${it.message}")
                    }
            }
        }
    }
    private fun getCalories(context: Context) {
        instance?.let { it ->
            context.let { it1 ->
                Fitness.getHistoryClient(it1, it)
                    .readDailyTotalFromLocalDevice(DataType.TYPE_CALORIES_EXPENDED)
                    .addOnSuccessListener {
                        var mCalories = if (it.dataPoints.isEmpty()) {
                            0f
                        } else {
                            it.dataPoints[0].getValue(Field.FIELD_CALORIES).asFloat()
                        }
                        mCalories = round(mCalories)
                        Singleton.personActivity.calories.value = mCalories.toInt()
                    }
                    .addOnCompleteListener {
                        return@addOnCompleteListener
                    }
                    .addOnFailureListener {
                        Log.d(LOG_TAG, "failure in getCalories - ${it.message}")
                    }
            }
        }
    }
    private fun getName(): String? = instance?.givenName
    private fun getPhotoUrl(): Uri? = instance?.photoUrl
    private fun getFullName(): String? = instance?.displayName
    private fun getEmail(): String? = instance?.email
}
