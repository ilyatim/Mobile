package com.example.testcoursework.utils.database.dao

import androidx.room.*
import com.example.testcoursework.data.model.entity.Record

@Dao
interface RecordsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record: Record)
    @Update
    fun update(record: Record)
    @Delete
    fun delete(record: Record)
    @Query("SELECT * FROM Record")
    fun getAll(): List<Record>
}