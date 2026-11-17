package com.khangnt6090.tvguide.data.repository

import com.khangnt6090.tvguide.data.local.TvShowDao
import com.khangnt6090.tvguide.data.local.TvShowEntity
import com.khangnt6090.tvguide.data.remote.TvMazeRemoteDataSource
import com.khangnt6090.tvguide.domain.model.TvShow
import com.khangnt6090.tvguide.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultTvShowRepository(
    private val tvShowDao: TvShowDao,
    private val remoteDataSource: TvMazeRemoteDataSource
) : TvShowRepository {

    override fun observeShows(): Flow<List<TvShow>> =
        tvShowDao.observeAll().map { entities ->
            entities.map(TvShowEntity::toDomain)
        }

    override suspend fun refreshShows(): Int {
        val latestShows = remoteDataSource.fetchShows()
        if (latestShows.isNotEmpty()) {
            tvShowDao.replaceAll(latestShows.map(TvShow::toEntity))
        }
        return latestShows.size
    }
}

private fun TvShowEntity.toDomain() = TvShow(
    id = id,
    name = name,
    genres = genres,
    premiered = premiered,
    summary = summary,
    imageUrl = imageUrl
)

private fun TvShow.toEntity() = TvShowEntity(
    id = id,
    name = name,
    genres = genres,
    premiered = premiered,
    summary = summary,
    imageUrl = imageUrl
)
