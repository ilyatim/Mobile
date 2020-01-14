package com.example.testcoursework.utils.database.dao

import androidx.room.*
import com.example.testcoursework.data.model.entity.Exercise

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exercise: Exercise)
    @Delete
    fun delete(exercise: Exercise)
    @Query("SELECT * FROM Exercises")
    fun getAllExercise(): List<Exercise>
    @Query("SELECT * FROM Exercises WHERE MAX(primaryKey)")
    fun getLastRecord(): Exercise
}
