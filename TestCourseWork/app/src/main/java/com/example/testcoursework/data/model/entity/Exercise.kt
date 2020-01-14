package com.example.testcoursework.data.model.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "Exercises")
data class Exercise(@PrimaryKey(autoGenerate = true) val primaryKey: Int,
                    @ColumnInfo(name = "date") @Embedded var date: Date,
                    @ColumnInfo(name = "exerciseName") var exName: String,
                    @ColumnInfo(name = "calories") var calories: Int,
                    @ColumnInfo(name = "distance")var distance: Int = 0)