package com.example.testcoursework.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(@PrimaryKey val id: Int,
                  @ColumnInfo(name = "recordCoveredDistance") var distance: Int,
                  @ColumnInfo(name = "recordCaloriesLost") var calories: Int,
                  @ColumnInfo(name = "recordStepsAmount") var steps: Int)