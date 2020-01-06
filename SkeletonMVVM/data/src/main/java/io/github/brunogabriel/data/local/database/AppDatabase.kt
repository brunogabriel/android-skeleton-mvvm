package io.github.brunogabriel.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.brunogabriel.data.local.database.dao.PhotoDao
import io.github.brunogabriel.data.local.models.PhotoCache

/**
 * Created by bruno on 2020-01-02
 */
@Database(
    version = 1,
    exportSchema = false,
    entities = [PhotoCache::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}