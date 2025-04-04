package com.example.learning2

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class Activity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: LocalDate,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val duration: Duration,
    val distance: Double,
    val trainName: String
)
fun Activity.toListItem(): ListItem {
    return ListItem(
        date = this.date,
        startTime = this.startTime,
        endTime = this.endTime,
        duration = this.duration,
        distance = this.distance,
        trainName = this.trainName
    )
}
