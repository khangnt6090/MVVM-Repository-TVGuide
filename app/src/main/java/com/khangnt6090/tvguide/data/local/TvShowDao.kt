package com.khangnt6090.tvguide.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_shows ORDER BY name COLLATE NOCASE ASC")
    fun observeAll(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(shows: List<TvShowEntity>)

    @Query("DELETE FROM tv_shows")
    suspend fun clearAll()

    @Transaction
    suspend fun replaceAll(shows: List<TvShowEntity>) {
        clearAll()
        insertAll(shows)
    }
}
