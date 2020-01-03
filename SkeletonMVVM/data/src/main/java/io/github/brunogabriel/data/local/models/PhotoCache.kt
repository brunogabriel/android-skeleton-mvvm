package io.github.brunogabriel.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by bruno on 2020-01-02
 */
@Entity(tableName = "photos")
data class PhotoCache(
    @PrimaryKey(autoGenerate = false)
    var id: Long = 0L,
    var title: String = "",
    var url: String = "",
    var thumbnailUrl: String = ""
)