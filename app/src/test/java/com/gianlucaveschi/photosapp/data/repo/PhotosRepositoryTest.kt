package com.gianlucaveschi.photosapp.data.repo

import com.BaseJunitTest
import com.gianlucaveschi.photosapp.data.model.PhotoItemApiModel
import com.gianlucaveschi.photosapp.data.network.PhotosService
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class PhotosRepositoryTest : BaseJunitTest<PhotosRepository>() {

    private val photosService: PhotosService = mockk(relaxed = true)

    override fun initSelf(): PhotosRepository = PhotosRepositoryImpl(photosService)

    @Test
    fun `GIVEN a successful response WHEN fetching list of photos THEN return correct response `() =
        runTest {
            coEvery { photosService.getPhotosList() } returns Response.success(
                listOf(
                    PhotoItemApiModel(
                        albumId = 0,
                        id = 1,
                        thumbnailUrl = "thumbnailUrl",
                        title = "title",
                        url = "url"
                    )
                )
            )

            val result = systemUnderTest.getPhotosList()

            assertEquals(result, listOf(mockedPhotoItemApiModel))
        }

    @Test
    fun `GIVEN a successful response WHEN fetching single photo THEN return correct response `() =
        runTest {
            coEvery { photosService.getPhotoItem(1) } returns Response.success(
                PhotoItemApiModel(
                    albumId = 0,
                    id = 1,
                    thumbnailUrl = "thumbnailUrl",
                    title = "title",
                    url = "url"
                )
            )

            val result = systemUnderTest.getPhotoItem(1)

            assertEquals(result, mockedPhotoItemApiModel)
        }

    companion object {
        val mockedPhotoItemApiModel = PhotoItemApiModel(
            albumId = 0,
            id = 1,
            thumbnailUrl = "thumbnailUrl",
            title = "title",
            url = "url"
        )
    }
}