package dev.sahildesai.thepodcastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.sahildesai.thepodcastapp.ui.navigation.NavigationGraph
import dev.sahildesai.thepodcastapp.ui.theme.ThePodcastAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThePodcastAppTheme {
                val navController = rememberNavController()
                NavigationGraph(navController)
            }
        }
    }
}