package io.github.brunogabriel.photolist

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import io.github.brunogabriel.coretest.RobotsRule
import io.github.brunogabriel.data.di.dataModules
import io.github.brunogabriel.data.di.inMemoryDatabase
import io.github.brunogabriel.data.local.database.dao.PhotoDao
import io.github.brunogabriel.data.local.models.PhotoCache
import io.github.brunogabriel.domain.di.domainModules
import io.github.brunogabriel.photolist.di.photoListModules
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

/**
 * Created by brunogabriel on 2020-01-04
 */
@RunWith(AndroidJUnit4::class)
class PhotoListActivityTest : KoinTest {
    @get: Rule
    val activityRule = ActivityTestRule(PhotoListActivity::class.java, true, false)

    @get: Rule
    val photoListActivityRobots = RobotsRule(PhotoListActivityRobot(activityRule))

    private val photoDao: PhotoDao by inject()

    @Before
    fun setup() {
        photoListActivityRobots.setup()
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().context)
            inMemoryDatabase = true
            loadKoinModules(dataModules)
            loadKoinModules(domainModules)
            loadKoinModules(photoListModules)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun shouldShowPhotos() {
        photoListActivityRobots {
            mockPhotos(6)
            showScreen()
            checkNumberOfItemsDisplayed(6)
        }
    }

    @Test
    fun shouldShowPhotoTitle() {
        photoListActivityRobots {
            mockPhotos(6)
            showScreen()
            checkDisplayPhotoTitle(0, "title1")
        }
    }

    @Test
    fun shouldShowTryAgain() {
        photoListActivityRobots {
            showScreen()
            checkDisplayTryAgainAfterClick()
        }
    }

    private fun mockPhotos(numberOfPhotos: Int) {
        photoDao.insertAll((1..numberOfPhotos).toList().map {
            PhotoCache(it.toLong(), "title$it", "url$it", "thumb$it")
        })
    }
}