package io.github.brunogabriel.photolist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.check
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.brunogabriel.core.ui.UiState
import io.github.brunogabriel.domain.entities.Photo
import io.github.brunogabriel.domain.usecases.FetchPhotoUseCases
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by bruno on 2020-01-04
 */
class PhotoListViewModelTest {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var photoListViewModel: PhotoListViewModel
    private lateinit var photoUseCases: FetchPhotoUseCases

    @Before
    fun setup() {
        photoUseCases = mock()
        photoListViewModel = PhotoListViewModel(photoUseCases, Schedulers.trampoline())
    }

    @Test
    fun `should emit value when fetch photos`() {
        // given
        val photos = listOf(Photo(1L, "", "", ""))
        whenever(photoUseCases.fetchPhotos(false)).thenReturn(Single.just(photos))
        val observerTest = mock<Observer<UiState<List<Photo>>>>()

        // when
        photoListViewModel.fetchPhotos()

        //then
        photoListViewModel.state.observeForever(observerTest)
        verify(observerTest).onChanged(check { uiState ->
            assertEquals(UiState.Success(photos), uiState)
        })
    }
}