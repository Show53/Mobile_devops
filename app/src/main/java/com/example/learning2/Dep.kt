package com.example.learning2

import android.content.Context
import androidx.room.Room

object Dep {
    lateinit var context: Context
    lateinit var db: MyDatabase
    fun initDatabase() {
        val myDB = Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            name = "mydb"
        ).build()
    }
}