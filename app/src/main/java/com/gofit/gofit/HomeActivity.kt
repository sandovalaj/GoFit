package com.gofit.gofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.niTraining -> {
                    replaceFragment(TrainingFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.niFavorites -> {
                    replaceFragment(FavoritesFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.niReport -> {
                    replaceFragment(ReportFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.niSettings -> {
                    replaceFragment(SettingsFragment())
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        replaceFragment(TrainingFragment())

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragContent, fragment)
            .commit()
    }
}

