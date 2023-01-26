package com.gianlucaveschi.photosapp.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gianlucaveschi.photosapp.domain.interactors.GetPhotoDetailUseCase
import com.gianlucaveschi.photosapp.presentation.model.mapper.mapToUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase
) : ViewModel() {

    private val _photoDetail = mutableStateOf(PhotoDetailState())
    val photoDetailState: State<PhotoDetailState> = _photoDetail

    fun getPhotoDetail(photoId: Int) {
        viewModelScope.launch {
            val photo = getPhotoDetailUseCase(photoId)
            val photoUiModel = photo?.mapToUiModel()
            _photoDetail.value = PhotoDetailState(photo = photoUiModel)
        }
    }
}