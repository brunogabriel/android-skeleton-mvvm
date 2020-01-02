package io.github.brunogabriel.domain.di

import io.github.brunogabriel.domain.usecases.FetchPhotosUseCases
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

/**
 * Created by bruno on 2020-01-02
 */
private val useCasesModule = module {
    factory {
        FetchPhotosUseCases(
            photoRepository = get(),
            scheduler = Schedulers.io()
        )
    }
}

val domainModule = listOf(useCasesModule)