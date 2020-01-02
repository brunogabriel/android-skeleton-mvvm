package io.github.brunogabriel.domain.usecases

import io.github.brunogabriel.domain.entities.Photo
import io.github.brunogabriel.domain.repository.PhotoRepository
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by bruno on 2020-01-02
 */
class FetchPhotosUseCases(
    private val photoRepository: PhotoRepository,
    private val scheduler: Scheduler
) {
    fun fetchPhotos(forceUpdate: Boolean): Single<List<Photo>> {
        return photoRepository.fetchPhotos(forceUpdate).subscribeOn(scheduler)
    }
}