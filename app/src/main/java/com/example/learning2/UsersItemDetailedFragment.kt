package com.example.learning2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Duration

class UsersItemDetailFragment : Fragment() {

    companion object {
        private const val ARG_NICKNAME = "nickname"
        private const val ARG_DATE = "date"
        private const val ARG_START_TIME = "startTime"
        private const val ARG_END_TIME = "endTime"
        private const val ARG_DURATION = "duration"
        private const val ARG_DISTANCE = "distance"
        private const val ARG_TRAIN_NAME = "trainName"

        fun newInstance(item: UsersListItem): UsersItemDetailFragment {
            val fragment = UsersItemDetailFragment()
            val args = Bundle().apply {
                putString(ARG_NICKNAME, item.nickname)
                putString(ARG_DATE, item.date.toString())
                putString(ARG_START_TIME, item.startTime.toString())
                putString(ARG_END_TIME, item.endTime.toString())
                putLong(ARG_DURATION, item.duration.seconds)
                putDouble(ARG_DISTANCE, item.distance)
                putString(ARG_TRAIN_NAME, item.trainName)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_community_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nickname = arguments?.getString(ARG_NICKNAME)
        val date = arguments?.getString(ARG_DATE)?.let { LocalDate.parse(it) }
        val startTime = arguments?.getString(ARG_START_TIME)?.let { LocalDateTime.parse(it) }
        val endTime = arguments?.getString(ARG_END_TIME)?.let { LocalDateTime.parse(it) }
        val duration = arguments?.getLong(ARG_DURATION)?.let { Duration.ofSeconds(it) }
        val distance = arguments?.getDouble(ARG_DISTANCE)
        val trainName = arguments?.getString(ARG_TRAIN_NAME)

        view.findViewById<TextView>(R.id.list_item_user_name).text = nickname
        view.findViewById<TextView>(R.id.list_item_date_time).text = date?.toString()
        view.findViewById<TextView>(R.id.list_item_distance_counter).text = "$distance км"
        view.findViewById<TextView>(R.id.list_item_duration).text = duration?.toString()
        view.findViewById<TextView>(R.id.expanded_title).text = trainName
        view.findViewById<TextView>(R.id.list_item_start_value_time).text = startTime?.toString()
        view.findViewById<TextView>(R.id.list_item_end_value_time).text = endTime?.toString()

        val backButton = view.findViewById<View>(R.id.toolbar_back)
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }




}