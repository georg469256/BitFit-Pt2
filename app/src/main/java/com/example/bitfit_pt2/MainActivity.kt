package com.example.bitfit_pt2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // define your fragments here
        val diaryFragment: Fragment = DiaryFragment()
        val dashboardFragment: Fragment = DashBoardFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val addFab: FloatingActionButton = findViewById(R.id.addFAB)

        addFab.setOnClickListener {
            val intent = Intent(this, AddEntryActivity::class.java)
            this.startActivity(intent)
        }

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.diaryTab -> fragment = diaryFragment
                R.id.dashboardTab -> fragment = dashboardFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.diaryTab

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.placeholder, fragment).commit()
    }

    }