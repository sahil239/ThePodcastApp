package dev.sahildesai.thepodcastapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable
import dev.sahildesai.thepodcastapp.model.common.PodcastModel
import dev.sahildesai.thepodcastapp.ui.podcast_details.PodcastDetailsScreen
import dev.sahildesai.thepodcastapp.ui.podcast_list.PodcastListScreen


@Serializable
object PodcastList

@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController, startDestination = PodcastList){
        composable<PodcastList>{
            PodcastListScreen(navController)
        }
        composable<PodcastModel> {
            PodcastDetailsScreen(navController)
        }

    }
}
