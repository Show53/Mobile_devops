package com.example.learning2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ActivityDao {
    @Insert
    fun add(activity: Activity)

    @Delete
    fun delete(activity: Activity)

    @Update
    fun update(activity: Activity)

    @Query("SELECT * FROM Activity")
    fun getAllActivities(): List<Activity>

}