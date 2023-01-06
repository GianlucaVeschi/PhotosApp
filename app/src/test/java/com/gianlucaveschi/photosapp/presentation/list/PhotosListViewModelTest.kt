package com.gianlucaveschi.photosapp.presentation.list

import com.BaseJunitTest
import com.gianlucaveschi.MainCoroutineRule
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotosListUseCase
import com.gianlucaveschi.photosapp.domain.model.PhotoItem
import com.gianlucaveschi.photosapp.presentation.model.PhotoItemUiModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PhotosListViewModelTest : BaseJunitTest<PhotosListViewModel>() {

    private val getPhotosListUseCase: GetPhotosListUseCase = mockk(relaxed = true)
    val dispatcher = StandardTestDispatcher()

    override fun initSelf() = PhotosListViewModel(getPhotosListUseCase)

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `initial state is correct`() = runTest {
        val currentState = systemUnderTest.photosList.value

        assertEquals(listOf<PhotoItemUiModel>(), currentState)
    }

    @Test
    fun `initial state is correct 2`() = runTest {
        coEvery { getPhotosListUseCase() } returns listOf(
            PhotoItem(
                albumId = 0,
                id = 1,
                thumbnailUrl = "thumbnailUrl",
                title = "title",
                url = "url"
            ),
            PhotoItem(
                albumId = 0,
                id = 2,
                thumbnailUrl = "thumbnailUrl",
                title = "title",
                url = "url"
            )
        )

        systemUnderTest.getPhotosList()
        // Await the change
        //dispatcher.scheduler.advanceUntilIdle()
        val result = systemUnderTest.photosList.value

        assertEquals(listOf(mockedPhotoItemUiModel, mockedPhotoItemUiModel2), result)
    }

    companion object {
        val mockedPhotoItemUiModel = PhotoItemUiModel(
            albumId = 0,
            id = 1,
            thumbnailUrl = "thumbnailUrl",
            title = "title",
            url = "url"
        )
        val mockedPhotoItemUiModel2 = PhotoItem(
            albumId = 0,
            id = 2,
            thumbnailUrl = "thumbnailUrl",
            title = "title",
            url = "url"
        )
    }
}
