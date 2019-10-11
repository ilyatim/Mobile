package com.example.testcoursework.data

data class PersonActivity(var countOfDrunkWater: Int, var coveredDistance: Float, var numberOfSteps: Int, var currentWeight: Float)
{
    fun increaseWater()
    {
        countOfDrunkWater++
    }
    fun decreaseWater()
    {
        countOfDrunkWater--
    }
}
