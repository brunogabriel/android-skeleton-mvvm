package io.github.brunogabriel.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.github.brunogabriel.data.local.models.PhotoCache
import io.reactivex.Single

/**
 * Created by bruno on 2020-01-02
 */
@Dao
interface PhotoDao {
    @Insert
    fun insertAll(photos: List<PhotoCache>)

    @Query("SELECT * FROM photos")
    fun findPhotos(): Single<List<PhotoCache>>

    @Transaction
    fun update(photos: List<PhotoCache>) {
        deleteAll()
        insertAll(photos)
    }

    @Query("DELETE FROM photos")
    fun deleteAll()
}