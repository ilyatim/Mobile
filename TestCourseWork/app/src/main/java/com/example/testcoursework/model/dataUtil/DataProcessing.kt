package com.example.testcoursework.model.dataUtil

import android.content.Context
import android.content.SharedPreferences
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.model.data.dataClass.PersonActivity
import com.example.testcoursework.ui.activity.MainActivity

object DataProcessing
{
    private const val PERSON_ACTIVITY_STRING: String = "person_activity"
    private var instance: SharedPreferences? = null

    fun getSharedPreferences(context: Context): SharedPreferences
    {
        if(instance == null)
            instance = context.getSharedPreferences("com.example.testcoursework", Context.MODE_PRIVATE)
        return instance!!
    }

    fun loadData(pref: SharedPreferences)
    {
        if(pref.contains(PERSON_ACTIVITY_STRING))
        {
            Singleton.personActivity = pref.getPersonActivityData(PERSON_ACTIVITY_STRING)
        }
    }
    fun saveData(person: PersonActivity)
    {
        val e = instance?.edit()
        e?.put(PERSON_ACTIVITY_STRING, person)
        e?.apply()
    }
    private fun SharedPreferences.Editor.put(string: String, person: PersonActivity)
    {
        putString(string, "PersonActivity")
        putInt("Number_of_drunk_water_+$string", person.countOfDrunkWater)
        putFloat("Covered_distance_+$string", person.coveredDistance)
        putInt("Number_of_steps_+$string", person.numberOfSteps)
        putFloat("Current_weight_+$string", person.currentWeight)
        putInt("Current_calories_+$string", person.calories)
    }
    private fun SharedPreferences.getPersonActivityData(string: String): PersonActivity
    {
        val countOfDrunkWater = getInt("Number_of_drunk_water_+$string", 0)
        val coveredDistance = getFloat("Covered_distance_+$string", 0.0f)
        val numberOfSteps = getInt("Number_of_steps_+$string", 0)
        val currentWeight = getFloat("Current_weight_+$string", 0.0f)
        val currentCalories = getInt("Current_calories_+$string", 0)
        return PersonActivity(
            countOfDrunkWater,
            coveredDistance,
            numberOfSteps,
            currentWeight,
            currentCalories
        )
    }
}