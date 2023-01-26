package com.gianlucaveschi.photosapp.presentation.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.photosapp.data.util.ConnectivityObserver
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotosListUseCase
import com.gianlucaveschi.photosapp.presentation.model.PhotoItemUiModel
import com.gianlucaveschi.photosapp.presentation.model.mapper.mapToUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosListViewModel @Inject constructor(
    private val getPhotosListUseCase: GetPhotosListUseCase,
    private val observeNetworkUseCase: ConnectivityObserver
) : ViewModel() {

    val photosList: MutableState<List<PhotoItemUiModel>?> =
        mutableStateOf(listOf())

    var connection: Flow<ConnectivityObserver.Status> =
        flow { ConnectivityObserver.Status.Unknown }

    init {
        observeConnection()
    }

    private fun observeConnection() {
        viewModelScope.launch {
            connection = observeNetworkUseCase.observe().onEach { connectionStatus ->
                when (connectionStatus) {
                    ConnectivityObserver.Status.Available -> {
                        getPhotosList()
                    }
                    ConnectivityObserver.Status.Unknown -> {}
                    ConnectivityObserver.Status.Lost -> {}
                    ConnectivityObserver.Status.Losing -> {}
                    ConnectivityObserver.Status.Unavailable -> {}
                }
            }
        }
    }

    private fun getPhotosList() {
        viewModelScope.launch {
            val photos = getPhotosListUseCase()
            photosList.value = photos?.mapToUiModel()
        }
    }
}


