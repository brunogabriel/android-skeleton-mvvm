package io.github.brunogabriel.data.remote.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import io.github.brunogabriel.data.di.networkModule
import io.github.brunogabriel.data.di.urlTest
import io.github.brunogabriel.data.remote.models.PhotoPayload
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import retrofit2.Retrofit

/**
 * Created by bruno on 2020-01-03
 */
@RunWith(AndroidJUnit4::class)
class PhotoServiceTest : KoinTest {
    @get: Rule
    val mockServer = WireMockRule()

    private lateinit var photoService: PhotoService
    private lateinit var retrofit: Retrofit

    @Before
    fun setup() {
        startKoin {
            modules(listOf(networkModule))
        }
        urlTest = "http://127.0.0.1:8080"
        retrofit = get()
        photoService = retrofit.create(PhotoService::class.java)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun shouldFetchPhotos() {
        // given
        val expected = listOf(
            PhotoPayload(1L, "title", "url", "thumb")
        )

        // when
        mockServer.stubFor(
            get(urlPathEqualTo("/photos"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody(
                            "[" +
                                    "    {" +
                                    "        \"id\": 1," +
                                    "        \"title\": \"title\"," +
                                    "        \"url\": \"url\"," +
                                    "        \"thumbnailUrl\": \"thumb\"" +
                                    "    }" +
                                    "]"
                        )
                )
        )

        // then
        photoService.fetchPhotos().test()
            .assertComplete()
            .assertValueAt(0, expected)
            .dispose()
    }
}