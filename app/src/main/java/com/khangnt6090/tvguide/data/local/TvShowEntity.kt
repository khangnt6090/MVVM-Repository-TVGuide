package com.khangnt6090.tvguide.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows")
data class TvShowEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val genres: String,
    val premiered: String?,
    val summary: String?,
    val imageUrl: String?
)
