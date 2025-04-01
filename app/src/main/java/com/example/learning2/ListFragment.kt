package com.example.learning2

import ListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Duration
import java.time.format.DateTimeFormatter

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val testData = listOf(
            ListItem(
                LocalDate.parse("2025-03-30", formatterDate),
                LocalDateTime.parse("2025-03-30T14:45"),
                LocalDateTime.parse("2025-03-30T16:05"),
                Duration.ofMinutes(80),
                0.0,
                "Качели"
            ),
            ListItem(
                LocalDate.parse("2025-03-28", formatterDate),
                LocalDateTime.parse("2025-03-28T08:30"),
                LocalDateTime.parse("2025-03-28T08:40"),
                Duration.ofMinutes(10),
                150.0,
                "Велосипед"
            ),
            ListItem(
                LocalDate.parse("2025-03-29", formatterDate),
                LocalDateTime.parse("2025-03-29T09:15"),
                LocalDateTime.parse("2025-03-29T09:30"),
                Duration.ofMinutes(15),
                100.0,
                "Серфинг"
            ),
            ListItem(
                LocalDate.parse("2025-03-30", formatterDate),
                LocalDateTime.parse("2025-03-30T14:45"),
                LocalDateTime.parse("2025-03-30T16:05"),
                Duration.ofMinutes(80),
                0.0,
                "Качели"
            ),
            ListItem(
                LocalDate.parse("2025-03-31", formatterDate),
                LocalDateTime.parse("2025-03-31T10:00"),
                LocalDateTime.parse("2025-03-31T11:00"),
                Duration.ofMinutes(60),
                200.0,
                "Плавание"
            ),
            ListItem(
                LocalDate.parse("2025-04-01", formatterDate),
                LocalDateTime.parse("2025-04-01T12:30"),
                LocalDateTime.parse("2025-04-01T13:30"),
                Duration.ofMinutes(60),
                180.0,
                "Йога"
            ),
            ListItem(
                LocalDate.parse("2025-04-02", formatterDate),
                LocalDateTime.parse("2025-04-02T15:00"),
                LocalDateTime.parse("2025-04-02T16:00"),
                Duration.ofMinutes(60),
                170.0,
                "Бег"
            ),
            ListItem(
                LocalDate.parse("2025-04-03", formatterDate),
                LocalDateTime.parse("2025-04-03T09:00"),
                LocalDateTime.parse("2025-04-03T10:00"),
                Duration.ofMinutes(60),
                250.0,
                "Теннис"
            ),
            ListItem(
                LocalDate.parse("2025-04-04", formatterDate),
                LocalDateTime.parse("2025-04-04T13:30"),
                LocalDateTime.parse("2025-04-04T14:30"),
                Duration.ofMinutes(60),
                160.0,
                "Гимнастика"
            ),
            ListItem(
                LocalDate.parse("2025-04-05", formatterDate),
                LocalDateTime.parse("2025-04-05T16:00"),
                LocalDateTime.parse("2025-04-05T17:00"),
                Duration.ofMinutes(60),
                190.0,
                "Баскетбол"
            ),
            ListItem(
                LocalDate.parse("2025-04-06", formatterDate),
                LocalDateTime.parse("2025-04-06T08:30"),
                LocalDateTime.parse("2025-04-06T09:30"),
                Duration.ofMinutes(60),
                220.0,
                "Футбол"
            )
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ListAdapter(testData, object : ListAdapter.OnItemClickListener {
            override fun onItemClick(item: ListItem) {
                val fragment = DetailFragment.newInstance(item)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
    }
}
