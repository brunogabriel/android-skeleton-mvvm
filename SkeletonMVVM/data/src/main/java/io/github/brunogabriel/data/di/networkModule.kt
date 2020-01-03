package io.github.brunogabriel.data.di

import androidx.annotation.VisibleForTesting
import io.github.brunogabriel.data.BuildConfig
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by bruno on 2020-01-02
 */
@VisibleForTesting
var urlTest: String? = null

val networkModule = module {
    factory {
        OkHttpClient.Builder()
            .readTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(40L, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(urlTest ?: BuildConfig.API_URL)
    }
}