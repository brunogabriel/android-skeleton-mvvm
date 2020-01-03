package io.github.brunogabriel.data.local.mapper

import io.github.brunogabriel.data.local.models.PhotoCache
import io.github.brunogabriel.domain.entities.Photo
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by bruno on 2020-01-03
 */
class PhotoCacheMapperTest {
    @Test
    fun `should map to photo`() {
        // given
        val cache = listOf(PhotoCache(199, "a", "b", "c"))

        // when
        val result = PhotoCacheMapper.mapToPhoto(cache)

        // then
        assertThat(result.size, `is`(1))
        assertThat(result[0], `is`(Photo(199, "a", "b", "c")))
    }

    @Test
    fun `should map to cache`() {
        // given
        val photos = listOf(Photo(199, "a", "b", "c"))

        // when
        val result = PhotoCacheMapper.mapToCache(photos)

        // then
        assertThat(result.size, `is`(1))
        assertThat(result[0], `is`(PhotoCache(199, "a", "b", "c")))
    }
}