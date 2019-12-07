package com.example.testcoursework.model.data.enumClass

enum class Gender(gender: String) {
    MEN("Мужской"),
    WOMEN("Женский"),
    OTHER("Не указано");

    val value: String
        get() = when(this) {
            MEN -> "Мужской"
            WOMEN -> "Женский"
            OTHER -> "Не указано"
        }
}
