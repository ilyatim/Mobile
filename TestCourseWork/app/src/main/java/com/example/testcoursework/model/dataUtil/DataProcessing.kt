package com.example.testcoursework.model.dataUtil

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.testcoursework.model.data.Singleton
import com.example.testcoursework.model.data.dataClass.Person
import com.example.testcoursework.model.data.dataClass.PersonActivity
import com.example.testcoursework.model.data.enumClass.Gender
import com.example.testcoursework.ui.activity.MainActivity
import com.example.testcoursework.utils.googleAccount.GoogleAccount

object DataProcessing {
    private const val PERSON_ACTIVITY_STRING: String = "person_activity"
    private const val PERSON_GENDER: String = "gender"

    private var instance: SharedPreferences? = null

    fun getSharedPreferences(context: Context): SharedPreferences {
        if (instance == null)
            instance = context.getSharedPreferences("com.example.testcoursework", Context.MODE_PRIVATE)
        return instance!!
    }
    fun loadData(pref: SharedPreferences) {
        if (pref.contains(PERSON_GENDER)) {
            Singleton.person.gender.value =  Gender.valueOf(pref.getPersonGender(PERSON_GENDER))
        }
    }
    fun saveData(person: Person) {
        val e = instance?.edit()
        e?.putString( PERSON_GENDER, person.gender.value?.name)
        e?.apply()
    }
    private fun SharedPreferences.getPersonGender(string: String): String {
        return getString(PERSON_GENDER, "Не указано") ?: "Не указано"
    }
}