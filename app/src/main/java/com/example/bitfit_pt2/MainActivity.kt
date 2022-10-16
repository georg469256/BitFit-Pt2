package com.example.bitfit_pt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diaryRV: RecyclerView = findViewById(R.id.diaryRV)
        val diaryList: MutableList<DisplayDiary> = listOf<DisplayDiary>().toMutableList()
        val diaryAdapter = DiaryAdapter(diaryList)
        diaryRV.adapter = diaryAdapter
        diaryRV.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            (application as DiaryApplication).db.diaryDao().getAllByDateDesc().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayDiary(
                        entity.id,
                        entity.title,
                        entity.date,
                        entity.entry
                    )
                }.also { mappedList ->
                    diaryList.clear()
                    diaryList.addAll(mappedList)
                    diaryAdapter.notifyDataSetChanged()
                }
            }
        }

        val addEntryButton: Button = findViewById(R.id.addEntryButton)
        addEntryButton.setOnClickListener {
            val intent = Intent(this, AddEntryActivity::class.java)
            this.startActivity(intent)
        }
    }
}