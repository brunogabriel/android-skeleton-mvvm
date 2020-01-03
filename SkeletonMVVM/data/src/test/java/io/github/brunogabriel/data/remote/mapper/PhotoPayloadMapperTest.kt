package io.github.brunogabriel.data.remote.mapper

import io.github.brunogabriel.data.remote.models.PhotoPayload
import io.github.brunogabriel.domain.entities.Photo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by bruno on 2020-01-03
 */
class PhotoPayloadMapperTest {
    @Test
    fun `should map to photo`() {
        // val
        val payload = listOf(PhotoPayload(1999, "a", "b", "c"))

        // when
        val result = PhotoPayloadMapper.mapToPhoto(payload)

        // then
        assertThat(result.size, `is`(1))
        assertThat(result[0], `is`(Photo(1999, "a", "b", "c")))
    }
}