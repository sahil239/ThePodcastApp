package dev.sahildesai.thepodcastapp.ui.podcast_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun PodcastListScreen(
    navController: NavController,
    viewModel: PodcastListViewModel = hiltViewModel()
){
    val podcastListState = viewModel.podcastListState.collectAsStateWithLifecycle()
    when(podcastListState) {
        PodcastListState.Loading -> LoadingData()
        else -> {
            Text(text = "In Progress")
        }
    }
}

@Composable
fun LoadingData(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

