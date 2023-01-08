package com.gianlucaveschi.photosapp.presentation.list

import com.BaseJunitTest
import com.gianlucaveschi.MainCoroutineRule
import com.gianlucaveschi.photosapp.data.util.ConnectivityObserver
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotosListUseCase
import com.gianlucaveschi.photosapp.domain.model.PhotoItem
import com.gianlucaveschi.photosapp.presentation.model.PhotoItemUiModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PhotosListViewModelTest : BaseJunitTest<PhotosListViewModel>() {

    private val getPhotosListUseCase: GetPhotosListUseCase = mockk(relaxed = true)
    private val connectivityObserver: ConnectivityObserver = mockk(relaxed = true)
    val dispatcher = StandardTestDispatcher()

    override fun initSelf() = PhotosListViewModel(
        getPhotosListUseCase,
        connectivityObserver
    )

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `initial state is correct`() = runTest {
        val currentState = systemUnderTest.photosList.value

        assertEquals(listOf<PhotoItemUiModel>(), currentState)
    }

    @Test
    @Ignore("assertEquals should assert that the objects have the same fields")
    fun `state is updated`() = runTest {
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
        dispatcher.scheduler.advanceUntilIdle()
        val result: List<PhotoItemUiModel>? = systemUnderTest.photosList.value

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
        val mockedPhotoItemUiModel2 = PhotoItemUiModel(
            albumId = 0,
            id = 2,
            thumbnailUrl = "thumbnailUrl",
            title = "title",
            url = "url"
        )
    }
}
