package com.khangnt6090.tvguide.domain.repository

import com.khangnt6090.tvguide.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    /** Continuously observes the local database. */
    fun observeShows(): Flow<List<TvShow>>

    /** Fetches the latest data and updates the local cache. */
    suspend fun refreshShows(): Int
}
