package dev.sahildesai.thepodcastapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable
import dev.sahildesai.thepodcastapp.ui.podcast_list.PodcastListScreen

@Composable
fun NavigationGraph(navController: NavHostController){
    NavHost(navController, startDestination = PodcastList){
        composable<PodcastList>{
            PodcastListScreen(navController)
        }
    }
}

@Serializable
object PodcastList