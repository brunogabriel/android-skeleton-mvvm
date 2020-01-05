package io.github.brunogabriel.photolist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import io.github.brunogabriel.coretest.Robots
import io.github.brunogabriel.coretest.atPosition
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.github.tomakehurst.wiremock.client.WireMock
import io.github.brunogabriel.coretest.hasItemCount
import io.github.brunogabriel.data.di.inMemoryDatabase
import io.github.brunogabriel.data.di.urlTest
import org.junit.Rule

/**
 * Created by brunogabriel on 2020-01-04
 */
class PhotoListActivityRobot(
    private val rule: ActivityTestRule<PhotoListActivity>
) : Robots {
    @get: Rule
    val mockServer = WireMockRule()

    override fun setup() {
        urlTest = "http://127.0.0.1:8080"
        inMemoryDatabase = true
    }

    fun showScreen() {
        rule.launchActivity(null)
    }

    fun checkNumberOfItemsDisplayed(numberOfItems: Int) {
        onView(withId(R.id.photos_recycler_view)).check(hasItemCount(numberOfItems))
    }

    fun checkDisplayPhotoTitle(position: Int, title: String) {
        onView(withId(R.id.photos_recycler_view))
            .check(matches(atPosition(position, hasDescendant(withText(title)))))
    }

    fun checkDisplayTryAgainAfterClick() {
        mockServerErrorResponse()
        onView(withId(R.id.try_again_view)).check(matches(isDisplayed()))
        onView(withId(R.id.try_again_button)).perform(click())
        onView(withId(R.id.try_again_view)).check(matches(isDisplayed()))
    }

    private fun mockServerErrorResponse() {
        mockServer.stubFor(
            WireMock.get(WireMock.urlPathEqualTo("/photos"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(404)
                )
        )
    }
}