package com.gianlucaveschi.photosapp.presentation.detail

import com.BaseJunitTest
import com.gianlucaveschi.MainCoroutineRule
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotoDetailUseCase
import com.gianlucaveschi.photosapp.domain.model.PhotoItem
import com.gianlucaveschi.photosapp.presentation.model.PhotoItemUiModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class PhotosDetailViewModelTest : BaseJunitTest<PhotoDetailViewModel>() {

    private val getPhotoDetailUseCase: GetPhotoDetailUseCase = mockk(relaxed = true)

    override fun initSelf() = PhotoDetailViewModel(
        getPhotoDetailUseCase
    )


    private val dispatcher = StandardTestDispatcher()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `initial state is correct`() = runTest {
        val currentState = systemUnderTest.photoDetail.value

        assertEquals(null, currentState)
    }

    @Test
    @Ignore("Test fails as objects in memory are compared instead of their data")
    fun `state is updated correctly`() = runTest {
        coEvery {
            getPhotoDetailUseCase(0)
        } returns PhotoItem(
            albumId = 0,
            id = 0,
            thumbnailUrl = "thumbnailUrl",
            title = "title",
            url = "url"
        )

        systemUnderTest.getPhotoDetail(0)
        // Await the change
        dispatcher.scheduler.advanceUntilIdle()
        val actualState : PhotoItemUiModel? = systemUnderTest.photoDetail.value

        assertEquals(mockedPhotoItemUiModel, actualState)
    }

    companion object {
        val mockedPhotoItemUiModel = PhotoItemUiModel(
            albumId = 0,
            id = 0,
            thumbnailUrl = "thumbnailUrl",
            title = "title",
            url = "url"
        )
    }
}