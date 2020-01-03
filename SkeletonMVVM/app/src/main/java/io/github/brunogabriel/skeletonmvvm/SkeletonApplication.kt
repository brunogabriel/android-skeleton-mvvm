package io.github.brunogabriel.skeletonmvvm

import android.app.Application
import io.github.brunogabriel.data.di.dataModules
import io.github.brunogabriel.domain.di.domainModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by bruno on 2020-01-02
 */
class SkeletonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SkeletonApplication)
            modules(dataModules + domainModules)
        }
    }
}