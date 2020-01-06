package io.github.brunogabriel.data.remote.service

import io.github.brunogabriel.data.remote.models.PhotoPayload
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by bruno on 2020-01-02
 */
interface PhotoService {
    @GET("/photos")
    fun fetchPhotos(): Single<List<PhotoPayload>>
}