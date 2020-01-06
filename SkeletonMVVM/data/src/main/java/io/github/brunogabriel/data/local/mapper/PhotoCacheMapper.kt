package io.github.brunogabriel.data.local.mapper

import io.github.brunogabriel.data.local.models.PhotoCache
import io.github.brunogabriel.domain.entities.Photo

/**
 * Created by bruno on 2020-01-03
 */
object PhotoCacheMapper {
    fun mapToPhoto(data: List<PhotoCache>) = data.map {
        Photo(
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl
        )
    }

    fun mapToCache(photos: List<Photo>) = photos.map {
        PhotoCache(
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl
        )
    }
}