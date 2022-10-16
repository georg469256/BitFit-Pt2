package com.example.bitfit_pt2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary_table")
    fun getAll(): Flow<List<DiaryEntity>>

    @Query("SELECT * FROM diary_table ORDER BY date Desc")
    fun getAllByDateDesc(): Flow<List<DiaryEntity>>

    @Insert
    fun insert(diaryEntity: DiaryEntity)

    @Insert
    fun insertAll(diaryList: List<DiaryEntity>)

    @Query("DELETE FROM diary_table WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM diary_table")
    fun deleteAll()
}