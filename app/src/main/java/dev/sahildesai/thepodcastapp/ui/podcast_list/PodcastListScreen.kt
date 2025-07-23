package dev.sahildesai.thepodcastapp.ui.podcast_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun PodcastListScreen(
    navController: NavController,
    viewModel: PodcastListViewModel = hiltViewModel()
){
    val podcasts = viewModel.podcastFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(podcasts.itemCount) { index ->
            podcasts[index]?.let {
                Text(text = it.id)
            }
        }

        podcasts.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingData() }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingData() }
                }
                loadState.append is LoadState.Error -> {
                    val error = loadState.append as LoadState.Error
                    item {
                        Text("Error: ${error.error.message}")
                    }
                }
            }
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

