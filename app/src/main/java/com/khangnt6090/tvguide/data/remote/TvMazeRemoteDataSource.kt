package com.khangnt6090.tvguide.data.remote

import com.khangnt6090.tvguide.domain.model.TvShow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class TvMazeRemoteDataSource(
    private val client: HttpClient
) {
    suspend fun fetchShows(): List<TvShow> {
        return client.get("https://api.tvmaze.com/shows") {
            parameter("page", 0)
        }
            .body<List<TvMazeShowDto>>()
            .take(50)
            .map { dto ->
                TvShow(
                    id = dto.id,
                    name = dto.name,
                    genres = dto.genres.joinToString(" • ").ifBlank { "Chưa phân loại" },
                    premiered = dto.premiered,
                    summary = dto.summary?.toPlainText(),
                    imageUrl = dto.image?.original ?: dto.image?.medium
                )
            }
    }
}

private fun String.toPlainText(): String =
    replace(Regex("<[^>]*>"), " ")
        .replace("&nbsp;", " ")
        .replace("&amp;", "&")
        .replace("&quot;", "\"")
        .replace("&#39;", "'")
        .replace(Regex("\\s+"), " ")
        .trim()
