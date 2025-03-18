package com.example.learning2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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
    }

    private fun switchToFragment(tag: String, fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        val activeFragment = fragmentManager.findFragmentById(R.id.nav_fragment)
        activeFragment?.let { transaction.hide(it) }

        var newFragment = fragmentManager.findFragmentByTag(tag)
        if (newFragment == null) {
            newFragment = fragment
            transaction.add(R.id.nav_fragment, newFragment, tag)
        } else {
            transaction.show(newFragment)
        }
        transaction.commit()
    }
}
