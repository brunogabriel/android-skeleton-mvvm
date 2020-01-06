package io.github.brunogabriel.domain.entities

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by bruno on 2020-01-02
 */
class PhotoTest {
    @Test
    fun `should be equals`() {
        // given
        val photo = Photo(123L, "title", "url", "thumb")

        // then
        assertThat(
            Photo(123L, "title", "url", "thumb"),
            `is`(photo)
        )
    }
}