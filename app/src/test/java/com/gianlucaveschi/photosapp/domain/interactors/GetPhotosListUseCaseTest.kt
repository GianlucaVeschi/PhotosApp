package com.gianlucaveschi.photosapp.domain.interactors

import com.BaseJunitTest
import com.gianlucaveschi.photosapp.data.model.PhotoItemApiModel
import com.gianlucaveschi.photosapp.data.util.NetworkResult
import com.gianlucaveschi.photosapp.domain.model.PhotoItem
import com.gianlucaveschi.photosapp.domain.repo.PhotosRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetPhotosListUseCaseTest : BaseJunitTest<GetPhotosListUseCase>() {

    private val photosRepository: PhotosRepository = mockk(relaxed = true)

    override fun initSelf(): GetPhotosListUseCase = GetPhotosListUseCase(photosRepository)

    @Test
    fun `WHEN fetching a list of items THEN map result to domain model`() = runTest {
        coEvery { photosRepository.getPhotosList() } returns NetworkResult.Success(
            listOf(
                PhotoItemApiModel(
                    albumId = 0,
                    id = 1,
                    thumbnailUrl = "thumbnailUrl",
                    title = "title",
                    url = "url"
                ),
                PhotoItemApiModel(
                    albumId = 0,
                    id = 2,
                    thumbnailUrl = "thumbnailUrl",
                    title = "title",
                    url = "url"
                )
            )
        )

        val result = systemUnderTest()

        assertEquals(listOf(mockedPhotoItem, mockedPhotoItem2), result)
    }

    @Test
    fun `WHEN fetching a list of items throws an error THEN return null`() = runTest {
        coEvery { photosRepository.getPhotosList() } returns NetworkResult.Error(
            400, "error"
        )

        val result = systemUnderTest()

        assertEquals(null, result)
    }

    @Test
    fun `WHEN fetching a list of items throws an exception THEN return null`() = runTest {
        coEvery { photosRepository.getPhotosList() } returns NetworkResult.Exception(
            Throwable()
        )

        val result = systemUnderTest()

        assertEquals(null, result)
    }

    companion object {
        val mockedPhotoItem = PhotoItem(
            albumId = 0,
            id = 1,
            thumbnailUrl = "thumbnailUrl",
            title = "title",
            url = "url"
        )

        val mockedPhotoItem2 = PhotoItem(
            albumId = 0,
            id = 2,
            thumbnailUrl = "thumbnailUrl",
            title = "title",
            url = "url"
        )
    }
}