package dev.sahildesai.thepodcastapp.ui.podcast_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import dev.sahildesai.thepodcastapp.model.common.PodcastModel
import dev.sahildesai.thepodcastapp.ui.navigation.PodcastDetails
import dev.sahildesai.thepodcastapp.ui.widgets.LoadImageFromUrl

@Composable
fun PodcastListScreen(
    navController: NavController,
    viewModel: PodcastListViewModel = hiltViewModel()
){
    val podcasts = viewModel.podcasts.collectAsLazyPagingItems()

    Column(Modifier.fillMaxSize().background(Color.White).padding(24.dp)) {
        Text(
            text = "Podcasts",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        val loadState = podcasts.loadState

        when (val refreshState = loadState.refresh) {
            is LoadState.Error -> {
                // Full-screen error UI
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ErrorUI(refreshState.error.message ?: "Unknown error")
                }
            } else -> {
                LazyColumn {
                    items(podcasts.itemCount) { index ->
                        podcasts[index]?.let {
                            PodcastListItem(it) {
                                navController.navigate(PodcastDetails(it))
                                viewModel.toggleFavorite(it.id, !it.isFavorite)
                            }
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
                                    ErrorUI(error.error.message.toString())
                                }
                            }
                        }
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

@Composable
private fun PodcastListItem(
    podcast: PodcastModel,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LoadImageFromUrl(
            title = "thumbnail",
            imageUrl = podcast.thumbnail,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(Modifier.width(16.dp))

        Column {
            Text(
                text = podcast.title,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = podcast.publisher,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            if (podcast.isFavorite) {
                Text(
                    text = "Favourited",
                    color = Color.Red,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Composable
private fun ErrorUI(message: String){
    Column (modifier = Modifier.fillMaxSize()){
        Text(message)
    }
}

