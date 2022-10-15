package com.example.bitfit_pt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addEntryButton: Button = findViewById(R.id.addEntryButton)
        addEntryButton.setOnClickListener {
            val intent = Intent(this, AddEntryActivity::class.java)
            this.startActivity(intent)
        }
    }
}