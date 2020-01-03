package io.github.brunogabriel.data.local.source

import io.github.brunogabriel.domain.entities.Photo
import io.reactivex.Single

/**
 * Created by bruno on 2020-01-03
 */
interface PhotoCacheDataSource {
    fun fetchPhotos(): Single<List<Photo>>
    fun insertData(data: List<Photo>)
    fun updateData(data: List<Photo>)
}