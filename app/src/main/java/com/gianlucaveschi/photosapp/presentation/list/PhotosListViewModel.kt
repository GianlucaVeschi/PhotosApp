package com.gianlucaveschi.photosapp.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.photosapp.data.util.ConnectivityObserver
import com.gianlucaveschi.photosapp.data.util.ConnectivityObserver.Status
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotosListUseCase
import com.gianlucaveschi.photosapp.presentation.model.mapper.mapToUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PhotosListViewModel @Inject constructor(
    private val getPhotosListUseCase: GetPhotosListUseCase,
    private val observeNetworkUseCase: ConnectivityObserver
) : ViewModel() {

    private val _photosListState = mutableStateOf(PhotosListState())
    val photosListState: State<PhotosListState> = _photosListState

    init {
        getPhotosList()
    }

    private fun getPhotosList() {
        observeNetworkUseCase.observe().onEach { connectionStatus ->
            when (connectionStatus) {
                Status.Available -> {
                    val photos = getPhotosListUseCase()?.mapToUiModel() ?: emptyList()
                    _photosListState.value = PhotosListState(photos = photos)
                }
                Status.Unavailable -> {
                    _photosListState.value = PhotosListState(error = "Connection is unavailable")
                }
                Status.Losing -> {
                    _photosListState.value = PhotosListState(error = "Losing connection")
                }
                Status.Lost -> {
                    _photosListState.value = PhotosListState(error = "Connection is lost")
                }
            }
        }.launchIn(viewModelScope)
    }
}