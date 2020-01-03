package io.github.brunogabriel.photolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.brunogabriel.core.ui.UiState
import io.github.brunogabriel.core.ui.UiStateSingle
import io.github.brunogabriel.core.viewmodel.BaseViewModel
import io.github.brunogabriel.domain.entities.Photo
import io.github.brunogabriel.domain.usecases.FetchPhotoUseCases
import io.reactivex.Scheduler

/**
 * Created by bruno on 2020-01-03
 */
class PhotoListViewModel(
    private val photoUseCases: FetchPhotoUseCases,
    private val uiScheduler: Scheduler
) : BaseViewModel() {

    private val _state = MutableLiveData<UiState<List<Photo>>>().apply {
        value = UiState.Loading
    }
    val state: LiveData<UiState<List<Photo>>>
        get() = _state

    fun fetchPhotos(forceUpdate: Boolean = false) {
        addDisposable(photoUseCases.fetchPhotos(forceUpdate)
            .compose(UiStateSingle())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    _state.postValue(it)
                }, {

                }
            ))
    }

    fun onTryAgain() {
        fetchPhotos(true)
    }
}