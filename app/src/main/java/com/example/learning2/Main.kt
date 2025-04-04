package com.example.learning2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.nav_fragment, ActivityFragment(), "ACTIVITY_FRAGMENT")
                .commit()
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_activity -> switchToFragment("ACTIVITY_FRAGMENT", ActivityFragment())
                R.id.navigation_profile -> switchToFragment("PROFILE_FRAGMENT", ProfileFragment())
            }
            true
        }

        val newActivityButton: ImageView = findViewById(R.id.new_activity)
        newActivityButton.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        showTabLayout()
    }

    private fun switchToFragment(tag: String, fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        val existingFragment = fragmentManager.findFragmentByTag(tag)

        if (existingFragment == null) {
            transaction.replace(R.id.nav_fragment, fragment, tag)
        } else {
            transaction.show(existingFragment)
        }

        transaction.commit()
    }

    fun hideTabLayout() {
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.visibility = View.GONE // Скрываем TabLayout
    }

    fun showTabLayout() {
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.visibility = View.VISIBLE // Показываем TabLayout
    }
}
