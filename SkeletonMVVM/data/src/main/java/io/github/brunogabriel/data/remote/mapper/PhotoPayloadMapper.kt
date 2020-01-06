package io.github.brunogabriel.data.remote.mapper

import io.github.brunogabriel.data.remote.models.PhotoPayload
import io.github.brunogabriel.domain.entities.Photo

/**
 * Created by bruno on 2020-01-02
 */
object PhotoPayloadMapper {
    fun mapToPhoto(photoPayLoads: List<PhotoPayload>) = photoPayLoads.map {
        Photo(
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl
        )
    }
}