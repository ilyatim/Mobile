package com.example.testcoursework.data.repo

import android.content.Context
import android.content.SharedPreferences
import com.example.testcoursework.data.model.personInfo.Singleton
import com.example.testcoursework.data.model.personInfo.Person
import com.example.testcoursework.data.model.classes.enumClass.Gender

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
        e?.putString(PERSON_GENDER, person.gender.value?.name)
        e?.apply()
    }
    private fun SharedPreferences.getPersonGender(string: String): String {
        return getString(PERSON_GENDER, "Не указано") ?: "Не указано"
    }
}