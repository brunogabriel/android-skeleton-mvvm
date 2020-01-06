package io.github.brunogabriel.data.remote.source

import io.github.brunogabriel.data.remote.mapper.PhotoPayloadMapper
import io.github.brunogabriel.data.remote.service.PhotoService
import io.github.brunogabriel.domain.entities.Photo
import io.reactivex.Single

/**
 * Created by bruno on 2020-01-03
 */
class RemotePhotoDataSourceImplementation(
    private val photoService: PhotoService
) : RemotePhotoDataSource {
    override fun fetchPhotos(): Single<List<Photo>> {
        return photoService.fetchPhotos().map {
            PhotoPayloadMapper.mapToPhoto(it)
        }
    }
}