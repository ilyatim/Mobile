package com.example.testcoursework.model.dataUtil

import android.content.Context
import android.content.SharedPreferences
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.model.data.dataClass.PersonActivity
import com.example.testcoursework.ui.activity.MainActivity
import com.example.testcoursework.utils.googleAccount.GoogleAccount

object DataProcessing
{
    private const val PERSON_ACTIVITY_STRING: String = "person_activity"
    private const val PERSON_GENDER: String = "gender"
    private var instance: SharedPreferences? = null

    fun getSharedPreferences(context: Context): SharedPreferences
    {
        if(instance == null)
            instance = context.getSharedPreferences("com.example.testcoursework", Context.MODE_PRIVATE)
        return instance!!
    }

    fun loadData(pref: SharedPreferences)
    {
        var gender: String = "-"
        if (pref.contains(PERSON_GENDER))
        {
            gender = pref.getPersonGender(PERSON_GENDER)
        }
    }
    fun saveData(person: PersonActivity)
    {
        val e = instance?.edit()
        e?.putString( PERSON_GENDER, person.person.value?.gender?.value)
        e?.apply()
    }
    private fun SharedPreferences.getPersonGender(string: String): String
    {
        return getString(PERSON_GENDER, "Non gender") ?: "Non gender"
    }
}