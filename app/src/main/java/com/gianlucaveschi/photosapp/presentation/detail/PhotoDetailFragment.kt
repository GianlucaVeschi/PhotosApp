package com.gianlucaveschi.photosapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gianlucaveschi.photosapp.presentation.screens.ErrorScreen
import com.gianlucaveschi.photosapp.presentation.screens.PhotoDetailScreen
import com.gianlucaveschi.photosapp.presentation.theme.PhotosAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailFragment : Fragment() {

    private val viewModel: PhotoDetailViewModel by viewModels()
    private val args: PhotoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PhotosAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        val photoId = args.photoId
                        viewModel.getPhotoDetail(photoId)
                        val photoDetail = viewModel.photoDetail.value
                        if (photoDetail != null) {
                            PhotoDetailScreen(photoDetail = photoDetail)
                        } else {
                            ErrorScreen()
                        }
                    }
                }
            }
        }
    }
}