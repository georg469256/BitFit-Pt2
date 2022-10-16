package com.example.bitfit_pt2

import android.app.Application

class DiaryApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}