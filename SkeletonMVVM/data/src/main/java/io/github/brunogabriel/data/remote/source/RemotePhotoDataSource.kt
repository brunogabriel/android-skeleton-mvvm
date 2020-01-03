package io.github.brunogabriel.data.remote.source

import io.github.brunogabriel.domain.entities.Photo
import io.reactivex.Single

/**
 * Created by bruno on 2020-01-03
 */
interface RemotePhotoDataSource {
    fun fetchPhotos(): Single<List<Photo>>
}