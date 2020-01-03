package io.github.brunogabriel.domain.di

import io.github.brunogabriel.domain.usecases.FetchPhotoUseCases
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

/**
 * Created by bruno on 2020-01-02
 */
val useCasesModule = module {
    factory {
        FetchPhotoUseCases(
            repository = get(),
            scheduler = Schedulers.io()
        )
    }
}

val domainModules = listOf(useCasesModule)