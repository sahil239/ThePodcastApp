package dev.sahildesai.thepodcastapp.ui.podcast_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

sealed class PodcastListState {
    data object Loading: PodcastListState()
    data class Success(val podcastList: List<String>): PodcastListState()
    data class Error(val message: String): PodcastListState()
}

@HiltViewModel
class PodcastListViewModel : ViewModel(){

    private val _podcastListState = MutableStateFlow<PodcastListState>(PodcastListState.Loading)
    val podcastListState = _podcastListState

    init {
        fetchPodcast()
    }

    fun fetchPodcast(){
        _podcastListState.value = PodcastListState.Loading
    }
}