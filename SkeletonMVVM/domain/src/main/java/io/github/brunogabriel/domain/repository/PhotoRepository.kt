package io.github.brunogabriel.domain.repository

import io.github.brunogabriel.domain.entities.Photo
import io.reactivex.Single

/**
 * Created by bruno on 2020-01-02
 */
interface PhotoRepository {
    fun fetch(forceUpdate: Boolean): Single<List<Photo>>
}