package com.example.testcoursework.utils.mFitness

import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType

object Fitness
{
    fun fitnessInit(): FitnessOptions
    {
        return FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_WRITE)
            .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED, FitnessOptions.ACCESS_WRITE)
            .addDataType(DataType.AGGREGATE_HYDRATION, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_HYDRATION, FitnessOptions.ACCESS_WRITE)
            .addDataType(DataType.AGGREGATE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_DISTANCE_DELTA, FitnessOptions.ACCESS_WRITE)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_WRITE)
            .build()
    }
}