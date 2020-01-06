package io.github.brunogabriel.core.ui

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

/**
 * Created by bruno on 2020-01-03
 */
sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Failed(val throwable: Throwable) : UiState<Nothing>()
}

class UiStateSingle<T> : SingleTransformer<T, UiState<T>> {
    override fun apply(upstream: Single<T>): SingleSource<UiState<T>> {
        return upstream
            .map {
                UiState.Success(it) as UiState<T>
            }
            .onErrorReturn {
                UiState.Failed(it)
            }
            .doOnSubscribe {
                UiState.Loading
            }
    }
}