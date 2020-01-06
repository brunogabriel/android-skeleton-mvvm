package io.github.brunogabriel.data.di

import io.github.brunogabriel.data.repository.PhotoRepositoryImplementation
import io.github.brunogabriel.domain.repository.PhotoRepository
import org.koin.dsl.module

/**
 * Created by bruno on 2020-01-02
 */

val repositoryModules = module {
    factory<PhotoRepository> {
        PhotoRepositoryImplementation(
            photoCacheDataSource = get(),
            remotePhotoDataSource = get()
        )
    }
}