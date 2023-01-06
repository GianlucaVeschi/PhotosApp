package com.gianlucaveschi.photosapp.domain.interactors

import com.BaseJunitTest
import com.gianlucaveschi.photosapp.data.model.PhotoItemApiModel
import com.gianlucaveschi.photosapp.data.repo.PhotosRepository
import com.gianlucaveschi.photosapp.domain.model.PhotoItem
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
        coEvery { photosRepository.getPhotosList() } returns listOf(
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

        val result = systemUnderTest()

        assertEquals(result, listOf(mockedPhotoItem, mockedPhotoItem2))
    }

    @Test
    fun `WHEN fetching a list of null items THEN return null`() = runTest {
        coEvery { photosRepository.getPhotosList() } returns null

        val result = systemUnderTest()

        assertEquals(result, null)
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