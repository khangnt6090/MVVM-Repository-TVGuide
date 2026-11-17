package com.khangnt6090.tvguide.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class TvMazeShowDto(
    val id: Int,
    val name: String,
    val genres: List<String> = emptyList(),
    val premiered: String? = null,
    val summary: String? = null,
    val image: TvMazeImageDto? = null
)

@Serializable
data class TvMazeImageDto(
    val medium: String? = null,
    val original: String? = null
)
