package com.example.learning2

import android.app.Application
import androidx.room.Room

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Dep.db = Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java, "db"
        ).build()
    }
}