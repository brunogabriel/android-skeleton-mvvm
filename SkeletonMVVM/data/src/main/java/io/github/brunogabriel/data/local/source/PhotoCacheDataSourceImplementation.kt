package io.github.brunogabriel.data.local.source

import io.github.brunogabriel.data.local.database.dao.PhotoDao
import io.github.brunogabriel.data.local.mapper.PhotoCacheMapper
import io.github.brunogabriel.domain.entities.Photo
import io.reactivex.Single

/**
 * Created by bruno on 2020-01-03
 */
class PhotoCacheDataSourceImplementation(
    private val photoDao: PhotoDao
) : PhotoCacheDataSource {

    override fun fetchPhotos(): Single<List<Photo>> {
        return photoDao.findPhotos().map {
            PhotoCacheMapper.mapToPhoto(it)
        }
    }

    override fun insertData(data: List<Photo>) {
        photoDao.insertAll(PhotoCacheMapper.mapToCache(data))
    }

    override fun updateData(data: List<Photo>) {
        photoDao.update(PhotoCacheMapper.mapToCache(data))
    }
}