package io.github.brunogabriel.data.local.database.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.brunogabriel.data.local.database.AppDatabase
import io.github.brunogabriel.data.local.models.PhotoCache
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Created by bruno on 2020-01-03
 */
@RunWith(AndroidJUnit4::class)
class PhotoDaoTest {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var photoDao: PhotoDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        photoDao = appDatabase.photoDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun shouldInsertAll() {
        // given
        val cache = listOf(PhotoCache(10L, "", "", ""), PhotoCache(20L, "", "", ""))

        // when
        photoDao.insertAll(cache)

        // then
        photoDao.findPhotos().test()
            .assertComplete()
            .assertValue { it[0].id == 10L }
            .assertValue { it[1].id == 20L }
            .dispose()
    }

    @Test
    fun shouldDeleteAll() {
        // given
        val cache = listOf(PhotoCache(10L, "", "", ""), PhotoCache(20L, "", "", ""))
        photoDao.insertAll(cache)
        photoDao.findPhotos().test()
            .assertComplete()
            .assertValue { it[0].id == 10L }
            .assertValue { it[1].id == 20L }
            .dispose()

        // when
        photoDao.deleteAll()

        // then
        photoDao.findPhotos().test()
            .assertComplete()
            .assertValue { it.isEmpty() }
            .dispose()
    }

    @Test
    fun shouldUpdateAll() {
        // given
        val cache = listOf(PhotoCache(10L, "", "", ""), PhotoCache(20L, "", "", ""))
        val cache2 = listOf(PhotoCache(100L, "", "", ""), PhotoCache(200L, "", "", ""))
        photoDao.insertAll(cache)
        photoDao.findPhotos().test()
            .assertComplete()
            .assertValue { it[0].id == 10L }
            .assertValue { it[1].id == 20L }
            .dispose()

        // when
        photoDao.update(cache2)

        // then
        photoDao.findPhotos().test()
            .assertComplete()
            .assertValue { it.size == 2 }
            .assertValue { it[0].id == 100L }
            .assertValue { it[1].id == 200L }
            .dispose()
    }
}