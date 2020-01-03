package io.github.brunogabriel.data.remote.models

import com.google.gson.annotations.SerializedName

/**
 * Created by bruno on 2020-01-02
 */
data class PhotoPayload(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
)