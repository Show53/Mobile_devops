package com.example.learning2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

class MyVM : ViewModel() {
    private val db = Dep.db
    val activities = MutableLiveData<List<Activity>>()
    init {
        loadActivities()
    }

    suspend fun addActivity(id: Int, date: LocalDate, startTime: LocalDateTime,
                           endTime: LocalDateTime, duration: Duration, distance: Double, trainName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            db.getActivityDao().add(Activity(
                id,
                date,
                startTime,
                endTime,
                duration,
                distance,
                trainName
            ))
            activities.postValue(db.getActivityDao().getAllActivities())
        }
    }
    private fun loadActivities() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = db.getActivityDao().getAllActivities()
            activities.postValue(data)
        }
    }
}