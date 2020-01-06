package io.github.brunogabriel.photolist.di

import io.github.brunogabriel.photolist.adapter.PhotoAdapter
import io.github.brunogabriel.photolist.viewmodel.PhotoListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by bruno on 2020-01-03
 */
val photoListModule = module {
    factory { PhotoAdapter() }
    viewModel {
        PhotoListViewModel(
            photoUseCases = get(),
            uiScheduler = AndroidSchedulers.mainThread()
        )
    }
}

val photoListModules = listOf(photoListModule)