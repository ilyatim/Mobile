package com.example.testcoursework.model.data

import com.example.testcoursework.model.data.dataClass.Person
import com.example.testcoursework.model.data.dataClass.PersonActivity

object Singleton {
    val personActivity: PersonActivity = PersonActivity()
    val person: Person = Person()
}