package dev.sahildesai.thepodcastapp.ui.podcast_details

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.sahildesai.thepodcastapp.model.common.PodcastModel
import dev.sahildesai.thepodcastapp.ui.widgets.LoadImageFromUrl
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import dev.sahildesai.thepodcastapp.R
import dev.sahildesai.thepodcastapp.utils.toAnnotatedHtml

@Composable
fun PodcastDetailsScreen(
    navController: NavController,
    viewModel: PodcastDetailsViewModel = hiltViewModel()
) {
    val podcast by viewModel.podcast.collectAsState()
    PodcastDetailScreen(podcast, navController::popBackStack, viewModel::toggleFavorite)

}

@Composable
private fun PodcastDetailScreen(
    podcast: PodcastModel,
    onBack: () -> Unit,
    onToggleFavorite: () -> Unit
) {

    Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(top = 16.dp)) {
        IconButton(onClick = onBack) {
            Icon(
                painter = painterResource(R.drawable.ic_back_chevron),
                contentDescription = "Back"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = podcast.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = podcast.publisher,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontStyle = FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(24.dp))

            LoadImageFromUrl(
                imageUrl = podcast.thumbnail,
                title = podcast.title,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onToggleFavorite() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .border(
                        width = 2.dp,
                        color = if (podcast.isFavorite) Color.Red else Color.White,
                        shape = RoundedCornerShape(20)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!podcast.isFavorite) Color.Red else Color.White
                ),
                shape = RoundedCornerShape(20)
            ) {

                AnimatedContent(
                    targetState = podcast.isFavorite,
                    label = "FavoriteButtonText"
                ) { isFav ->
                    Text(
                        text = if (isFav) "Favourited" else "Favourite",
                        color = if (isFav) Color.Red else Color.White
                    )

                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = podcast.description.toAnnotatedHtml(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}