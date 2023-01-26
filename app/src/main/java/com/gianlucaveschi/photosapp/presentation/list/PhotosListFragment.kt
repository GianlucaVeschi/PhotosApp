package com.gianlucaveschi.photosapp.presentation.list

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
import androidx.navigation.fragment.NavHostFragment
import com.gianlucaveschi.photosapp.presentation.screens.ErrorScreen
import com.gianlucaveschi.photosapp.presentation.screens.LoadingScreen
import com.gianlucaveschi.photosapp.presentation.screens.PhotosListScreen
import com.gianlucaveschi.photosapp.presentation.theme.PhotosAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosListFragment : Fragment() {

    private val photosListViewModel: PhotosListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PhotosAppTheme {
                    val state = photosListViewModel.photosListState.value
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        if (state.photos.isNotEmpty()) {
                            PhotosListScreen(
                                photos = state.photos,
                                onPhotoItemClicked = { photoId ->
                                    val destination = PhotosListFragmentDirections
                                        .actionListFragmentToDetailFragment(photoId)
                                    NavHostFragment
                                        .findNavController(this@PhotosListFragment)
                                        .navigate(directions = destination)
                                }
                            )
                        }
                        if (state.error.isNotBlank()) {
                            ErrorScreen()
                        }
                        if (state.isLoading) {
                            LoadingScreen()
                        }
                    }
                }
            }
        }
    }
}
