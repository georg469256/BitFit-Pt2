package com.example.bitfit_pt2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

class DashBoardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dash_board, container, false)

        val avgText: TextView = view.findViewById(R.id.avgText)
        val minText: TextView = view.findViewById(R.id.minText)
        val maxText: TextView = view.findViewById(R.id.maxText)
        val diaryList: MutableList<DisplayDiary> = listOf<DisplayDiary>().toMutableList()

        var total = 0.0
        var count = 0.0
        var min = 0
        var max = 0

        lifecycleScope.launch {
            (activity?.application as DiaryApplication).db.diaryDao().getAllByDateDesc().collect { databaseList ->
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
                }
                if(diaryList.size > 0) {
                    val words = diaryList[0].entry?.split(" ")
                    if (words != null) {
                        min = words.size
                        max = words.size
                    }
                }

                diaryList.forEach { displayDiary: DisplayDiary ->
                    count++
                    val words = displayDiary.entry?.split(" ")
                    if(words != null) {
                        total += words.size
                        min = min(min, words.size)
                        max = max(max, words.size)
                    }
                }
                ("Average words per entry: " + (total/count).toInt()).also { avgText.text = it }
                ("Minimum words per entry: $min").also { minText.text = it }
                ("Maximum words per entry: $max").also { maxText.text = it }
            }
        }


        return view
    }

    companion object {
        fun newInstance(): DashBoardFragment{
            return DashBoardFragment()
        }
    }
}