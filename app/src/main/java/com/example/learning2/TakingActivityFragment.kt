package com.example.learning2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class TakingActivityFragment : Fragment()  {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_taking_new_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bicycleActivity = view.findViewById<LinearLayout>(R.id.bicycle_activity)
        val runningActivity = view.findViewById<LinearLayout>(R.id.running_activity)
        val swimmingActivity = view.findViewById<LinearLayout>(R.id.swimming_activity)

        bicycleActivity.setOnClickListener {
            runningActivity.isSelected = false
            swimmingActivity.isSelected = false
            bicycleActivity.isSelected = true
        }

        runningActivity.setOnClickListener {

            bicycleActivity.isSelected = false
            swimmingActivity.isSelected = false
            runningActivity.isSelected = true
        }

        swimmingActivity.setOnClickListener {
            bicycleActivity.isSelected = false
            runningActivity.isSelected = false
            swimmingActivity.isSelected = true
        }

        val buttonStartActivity = view.findViewById<Button>(R.id.start_activity)
        buttonStartActivity.setOnClickListener {
            val resultActivity = if (bicycleActivity.isSelected) {
                "Велосипед"
            } else if (runningActivity.isSelected) {
                "Бег"
            } else if (swimmingActivity.isSelected) {
                "Плавание"
            } else {
                "Не выбрано"
            }


            Log.d("ActivitySelection", "Selected activity: $resultActivity")


            if (resultActivity != "Не выбрано") {
                val fragment = InActivityFragment().apply {
                    arguments = Bundle().apply {
                        putString("ACTIVITY_TYPE", resultActivity)
                    }
                }
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            } else {
                Log.d("ActivitySelection", "No activity selected.")
            }
        }
    }
}