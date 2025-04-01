package com.example.learning2

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

data class UsersListItem(
    val nickname: String,           // Никнейм человека
    val date: LocalDate,            // Дата тренировки
    val startTime: LocalDateTime,   // Время начала тренировки
    val endTime: LocalDateTime,     // Время окончания тренировки
    val duration: Duration,         // Продолжительность тренировки
    val distance: Double,           // Пройденная дистанция в км
    val trainName: String           // Название тренировки
)
