package io.github.brunogabriel.data.remote.source

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.brunogabriel.data.remote.models.PhotoPayload
import io.github.brunogabriel.data.remote.service.PhotoService
import io.github.brunogabriel.domain.entities.Photo
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by bruno on 2020-01-03
 */
class RemotePhotoDataSourceImplementationTest {
    private lateinit var remotePhotoDataSource: RemotePhotoDataSource
    private lateinit var photoService: PhotoService

    @Before
    fun setup() {
        photoService = mock()
        remotePhotoDataSource = RemotePhotoDataSourceImplementation(photoService)
    }

    @Test
    fun `should fetch photos`() {
        // given
        val photos = listOf(Photo(1000L, "title", "url", "thumb"))
        val photosPayload = listOf(PhotoPayload(1000L, "title", "url", "thumb"))
        whenever(photoService.fetchPhotos()).thenReturn(Single.just(photosPayload))

        // when
        val result = remotePhotoDataSource.fetchPhotos()

        // then
        result.test()
            .assertValues(photos)
            .dispose()
    }
}