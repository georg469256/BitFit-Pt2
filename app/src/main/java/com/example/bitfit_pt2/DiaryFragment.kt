package com.example.bitfit_pt2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class DiaryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_diary, container, false)
        val diaryRV: RecyclerView = view.findViewById(R.id.diaryRV)
        val diaryList: MutableList<DisplayDiary> = listOf<DisplayDiary>().toMutableList()
        val diaryAdapter = DiaryAdapter(diaryList)
        diaryRV.adapter = diaryAdapter
        diaryRV.layoutManager = LinearLayoutManager(context)

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
                    diaryAdapter.notifyDataSetChanged()
                }
            }
        }
        return view
    }

    companion object {
        fun newInstance(): DiaryFragment{
           return DiaryFragment()
        }
    }
}