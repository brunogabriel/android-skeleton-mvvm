package io.github.brunogabriel.domain.entities

/**
 * Created by bruno on 2020-01-02
 */
data class Photo(
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)