package io.github.brunogabriel.data.local.source

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.brunogabriel.data.local.database.dao.PhotoDao
import io.github.brunogabriel.data.local.models.PhotoCache
import io.github.brunogabriel.domain.entities.Photo
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by bruno on 2020-01-03
 */
class PhotoCacheDataSourceImplementationTest {
    private lateinit var photoDao: PhotoDao
    private lateinit var photoCacheDataSource: PhotoCacheDataSource

    @Before
    fun setup() {
        photoDao = mock()
        photoCacheDataSource = PhotoCacheDataSourceImplementation(photoDao)
    }

    @Test
    fun `should fetch photos`() {
        // given
        val cache = listOf(PhotoCache(1L, "anyTitle", "anyUrl", "anyThumbnailUrl"))
        whenever(photoDao.findPhotos()).thenReturn(Single.just(cache))

        // when
        val result = photoCacheDataSource.fetchPhotos()

        // then
        result
            .test()
            .assertValues(listOf(Photo(1L, "anyTitle", "anyUrl", "anyThumbnailUrl")))
            .dispose()
    }

    @Test
    fun `should insert data`() {
        // given
        val photos = listOf(Photo(1L, "anyTitle", "anyUrl", "anyThumbnailUrl"))
        val cache = listOf(PhotoCache(1L, "anyTitle", "anyUrl", "anyThumbnailUrl"))

        // when
        photoCacheDataSource.insertData(photos)

        // then
        verify(photoDao).insertAll(cache)
    }

    @Test
    fun `should update data`() {
        // given
        val photos = listOf(Photo(1L, "anyTitle", "anyUrl", "anyThumbnailUrl"))
        val cache = listOf(PhotoCache(1L, "anyTitle", "anyUrl", "anyThumbnailUrl"))

        // when
        photoCacheDataSource.updateData(photos)

        // then
        verify(photoDao).update(cache)
    }

}