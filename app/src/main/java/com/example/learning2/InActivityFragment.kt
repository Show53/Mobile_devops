package com.example.learning2

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Locale
import kotlin.random.Random

class InActivityFragment : Fragment() {
    private var seconds = 0
    private var running = true
    private lateinit var handler: Handler
    private lateinit var myVM: MyVM
    private lateinit var startTime: LocalDateTime

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_in_new_activity, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myVM = ViewModelProvider(this).get(MyVM::class.java)

        val activityTypeText = view.findViewById<TextView>(R.id.activity_type)
        val distanceCounter = view.findViewById<TextView>(R.id.distance_counter)
        val timeCounter = view.findViewById<TextView>(R.id.time_counter)
        val activityType = arguments?.getString("ACTIVITY_TYPE", "Тренировка") ?: "Тренировка"
        activityTypeText.text = activityType
        val randomDistance = Random.nextInt(1, 20)
        distanceCounter.text = "$randomDistance км"

        startTime = LocalDateTime.now()
        handler = Handler(Looper.getMainLooper())
        runTimer(timeCounter)

        view.findViewById<View>(R.id.finish_new_activity).setOnClickListener {
            finishActivity(randomDistance, activityType)
        }
    }

    private fun finishActivity(distance: Int, activityType: String) {
        running = false
        val endTime = LocalDateTime.now()
        val duration = Duration.between(startTime, endTime)

        lifecycleScope.launch {
            myVM.addActivity(
                id = 0,
                date = LocalDate.now(),
                startTime = startTime,
                endTime = endTime,
                duration = duration,
                distance = distance.toDouble(),
                trainName = activityType
            )
        }

        requireActivity().finish()
    }

    private fun runTimer(timeCounter: TextView) {
        handler.post(object : Runnable {
            override fun run() {
                if (running) {
                    seconds++
                    val minutes = seconds / 60
                    val secs = seconds % 60
                    timeCounter.text = String.format(Locale.US, "%02d:%02d", minutes, secs)
                    handler.postDelayed(this, 1000)
                }
            }
        })
    }
}