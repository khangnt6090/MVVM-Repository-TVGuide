package com.khangnt6090.tvguide.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TvGuideDatabase : RoomDatabase() {
    abstract fun tvShowDao(): TvShowDao

    companion object {
        @Volatile
        private var INSTANCE: TvGuideDatabase? = null

        fun getInstance(context: Context): TvGuideDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TvGuideDatabase::class.java,
                    "tv_guide_database"
                )
                    // Suitable for this small teaching demo. Production apps should add migrations.
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
