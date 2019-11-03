package com.example.testcoursework.model.data.dataClass

data class PersonActivity(var countOfDrunkWater: Int,
                          var coveredDistance: Float,
                          var numberOfSteps: Int,
                          var currentWeight: Float,
                          var calories: Int)
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
