package io.github.brunogabriel.data.local.models

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by bruno on 2020-01-03
 */
class PhotoCacheTest {
    @Test
    fun `should be equals`() {
        // given
        val input = PhotoCache(199, "a", "b", "c")

        assertThat(input, `is`(PhotoCache(199, "a", "b", "c")))
    }
}