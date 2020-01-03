package io.github.brunogabriel.domain.usecases

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.brunogabriel.domain.entities.Photo
import io.github.brunogabriel.domain.repository.PhotoRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * Created by bruno on 2020-01-02
 */
class FetchPhotoUseCasesTest {
    private lateinit var repository: PhotoRepository
    private lateinit var fetchPhotoUseCases: FetchPhotoUseCases

    @Before
    fun setup() {
        repository = mock()
        fetchPhotoUseCases = FetchPhotoUseCases(repository, Schedulers.trampoline())
    }

    @Test
    fun `should fetch photos`() {
        // given
        val photos = listOf(
            Photo(1L, "a", "a", "a"),
            Photo(2L, "b", "b", "b")
        )
        whenever(repository.fetch(any())).doReturn(Single.just(photos))

        // when
        val expected = fetchPhotoUseCases.fetchPhotos(true)

        // then
        expected.test()
            .assertValueAt(0, photos)
            .assertComplete()
            .dispose()
    }
}