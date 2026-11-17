package com.khangnt6090.tvguide.di

import android.content.Context
import com.khangnt6090.tvguide.data.local.TvGuideDatabase
import com.khangnt6090.tvguide.data.remote.TvMazeRemoteDataSource
import com.khangnt6090.tvguide.data.repository.DefaultTvShowRepository
import com.khangnt6090.tvguide.domain.repository.TvShowRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class AppContainer(context: Context) {
    private val httpClient = HttpClient(CIO) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }

    private val database = TvGuideDatabase.getInstance(context)

    val tvShowRepository: TvShowRepository = DefaultTvShowRepository(
        tvShowDao = database.tvShowDao(),
        remoteDataSource = TvMazeRemoteDataSource(httpClient)
    )
}
