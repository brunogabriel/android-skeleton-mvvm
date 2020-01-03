package io.github.brunogabriel.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.brunogabriel.data.local.source.PhotoCacheDataSource
import io.github.brunogabriel.data.remote.source.RemotePhotoDataSource
import io.github.brunogabriel.domain.entities.Photo
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by bruno on 2020-01-03
 */
class PhotoRepositoryImplementationTest {
    private lateinit var photoCacheDataSource: PhotoCacheDataSource
    private lateinit var remotePhotoDataSource: RemotePhotoDataSource
    private lateinit var photoRepository: PhotoRepositoryImplementation

    @Before
    fun setup() {
        photoCacheDataSource = mock()
        remotePhotoDataSource = mock()
        photoRepository = PhotoRepositoryImplementation(photoCacheDataSource, remotePhotoDataSource)
    }

    @Test
    fun `should fetch photos from cache`() {
        // given
        val photos = listOf(
            Photo(1, "", "", ""),
            Photo(2, "", "", "")
        )
        whenever(photoCacheDataSource.fetchPhotos()).thenReturn(Single.just(photos))

        // when
        val result = photoRepository.fetch(false)

        // then
        result
            .test()
            .assertValues(photos)
            .dispose()
    }

    @Test
    fun `should force update from remote data source`() {
        // given
        val photos = listOf(
            Photo(1, "", "", ""),
            Photo(2, "", "", "")
        )
        whenever(remotePhotoDataSource.fetchPhotos()).thenReturn(Single.just(photos))

        // when
        val result = photoRepository.fetch(true)

        // then
        result
            .test()
            .assertValues(photos)
            .assertOf {
                verify(photoCacheDataSource).updateData(photos)
            }
            .dispose()
    }

    @Test
    fun `should request and insert data when cache is empty`() {
        // given
        val photos = listOf(
            Photo(1, "", "", ""),
            Photo(2, "", "", "")
        )
        whenever(photoCacheDataSource.fetchPhotos()).thenReturn(Single.just(emptyList()))
        whenever(remotePhotoDataSource.fetchPhotos()).thenReturn(Single.just(photos))

        // when
        val result = photoRepository.fetch(false)

        // then
        result
            .test()
            .assertValues(photos)
            .assertOf {
                verify(photoCacheDataSource).insertData(photos)
            }
            .dispose()
    }
}