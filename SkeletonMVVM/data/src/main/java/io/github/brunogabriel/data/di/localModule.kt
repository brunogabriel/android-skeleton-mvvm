package io.github.brunogabriel.data.di

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import io.github.brunogabriel.data.local.database.AppDatabase
import io.github.brunogabriel.data.local.source.PhotoCacheDataSource
import io.github.brunogabriel.data.local.source.PhotoCacheDataSourceImplementation
import org.koin.dsl.module

/**
 * Created by bruno on 2020-01-02
 */
@VisibleForTesting
var inMemoryDatabase = false

val localModule = module {
    single {
        if (inMemoryDatabase) {
            Room.inMemoryDatabaseBuilder(get(), AppDatabase::class.java).build()
        } else {
            Room.databaseBuilder(
                get(),
                AppDatabase::class.java,
                "app-database"
            ).build()
        }
    }

    // DAOs factory
    factory { get<AppDatabase>().photoDao() }

    // source
    factory<PhotoCacheDataSource> {
        PhotoCacheDataSourceImplementation(
            photoDao = get()
        )
    }
}