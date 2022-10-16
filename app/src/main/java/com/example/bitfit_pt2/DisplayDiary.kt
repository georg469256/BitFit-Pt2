package com.example.bitfit_pt2

import java.time.Instant

data class DisplayDiary (
    val id: Long?,
    val title: String?,
    val date: Instant?,
    val entry: String?
)