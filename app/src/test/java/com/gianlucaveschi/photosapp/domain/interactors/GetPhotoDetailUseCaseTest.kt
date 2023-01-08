package com.gianlucaveschi.photosapp.domain.interactors

import com.BaseJunitTest
import com.gianlucaveschi.photosapp.data.model.PhotoItemApiModel
import com.gianlucaveschi.photosapp.data.repo.PhotosRepository
import com.gianlucaveschi.photosapp.data.util.NetworkResult
import com.gianlucaveschi.photosapp.domain.model.PhotoItem
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetPhotoDetailUseCaseTest : BaseJunitTest<GetPhotoDetailUseCase>() {

    private val photosRepository: PhotosRepository = mockk(relaxed = true)

    override fun initSelf(): GetPhotoDetailUseCase = GetPhotoDetailUseCase(photosRepository)

    @Test
    fun `WHEN fetching an item THEN map result to domain model`() = runTest {
        coEvery {
            photosRepository.getPhotoItem(1)
        } returns NetworkResult.Success(
            PhotoItemApiModel(
                albumId = 0,
                id = 1,
                thumbnailUrl = "thumbnailUrl",
                title = "title",
                url = "url"
            )
        )


        val result = systemUnderTest(1)

        assertEquals(result, mockedPhotoItem)
    }

    @Test
    fun `WHEN fetching a list of null items THEN return null`() = runTest {
        coEvery {
            photosRepository.getPhotoItem(1)
        } returns NetworkResult.Error(400, "error")

        val result = systemUnderTest(1)

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
    }
}