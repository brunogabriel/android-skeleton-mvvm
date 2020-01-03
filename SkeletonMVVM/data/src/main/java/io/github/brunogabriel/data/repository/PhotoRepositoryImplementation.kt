package io.github.brunogabriel.data.repository

import io.github.brunogabriel.data.local.source.PhotoCacheDataSource
import io.github.brunogabriel.data.remote.source.RemotePhotoDataSource
import io.github.brunogabriel.domain.entities.Photo
import io.github.brunogabriel.domain.repository.PhotoRepository
import io.reactivex.Single

/**
 * Created by bruno on 2020-01-02
 */
class PhotoRepositoryImplementation(
    private val photoCacheDataSource: PhotoCacheDataSource,
    private val remotePhotoDataSource: RemotePhotoDataSource
) : PhotoRepository {

    override fun fetch(forceUpdate: Boolean): Single<List<Photo>> {
        return if (forceUpdate) {
            execute(forceUpdate)
        } else {
            photoCacheDataSource.fetchPhotos()
                .flatMap { result ->
                    when {
                        result.isEmpty() -> execute(false)
                        else -> Single.just(result)
                    }
                }
        }
    }

    private fun execute(forceUpdate: Boolean): Single<List<Photo>> {
        return remotePhotoDataSource.fetchPhotos()
            .flatMap {
                if (forceUpdate) {
                    photoCacheDataSource.updateData(it)
                } else {
                    photoCacheDataSource.insertData(it)
                }
                Single.just(it)
            }
    }
}