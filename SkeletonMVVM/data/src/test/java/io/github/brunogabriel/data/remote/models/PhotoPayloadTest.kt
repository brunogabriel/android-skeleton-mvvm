package io.github.brunogabriel.data.remote.models

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by bruno on 2020-01-03
 */
class PhotoPayloadTest {
    @Test
    fun `should be equals`() {
        // given
        val photoPayload = PhotoPayload(123, "1", "2", "3")

        // then
        assertThat(
            photoPayload, `is`(
                PhotoPayload(123, "1", "2", "3")
            )
        )
    }
}