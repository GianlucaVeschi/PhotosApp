package com.gianlucaveschi.photosapp.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.gianlucaveschi.photosapp.data.util.ConnectivityObserver
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
                    val status by photosListViewModel.connection.collectAsState(
                        initial = ConnectivityObserver.Status.Unknown
                    )
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        when (status) {
                            ConnectivityObserver.Status.Available -> {
                                val photosList = photosListViewModel.photosList.value
                                if (photosList != null) {
                                    PhotosListScreen(
                                        photos = photosList,
                                        onPhotoItemClicked = { photoId ->
                                            val destination = PhotosListFragmentDirections
                                                .actionListFragmentToDetailFragment(photoId)
                                            NavHostFragment
                                                .findNavController(this@PhotosListFragment)
                                                .navigate(directions = destination)
                                        }
                                    )
                                }
                            }
                            ConnectivityObserver.Status.Unknown -> {
                                LoadingScreen()
                            }
                            else -> {
                                ErrorScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}
