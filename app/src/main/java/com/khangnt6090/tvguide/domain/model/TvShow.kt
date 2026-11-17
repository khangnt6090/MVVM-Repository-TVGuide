package com.khangnt6090.tvguide.domain.model

data class TvShow(
    val id: Int,
    val name: String,
    val genres: String,
    val premiered: String?,
    val summary: String?,
    val imageUrl: String?
)
