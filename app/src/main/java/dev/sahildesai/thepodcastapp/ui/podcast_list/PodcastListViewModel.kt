package dev.sahildesai.thepodcastapp.ui.podcast_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sahildesai.thepodcastapp.repository.IPodcastRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

sealed class PodcastListState {
    data object Loading: PodcastListState()
    data class Success(val podcastList: List<String>): PodcastListState()
    data class Error(val message: String): PodcastListState()
}

@HiltViewModel
class PodcastListViewModel @Inject constructor(
    private val repository: IPodcastRepository
) : ViewModel(){

    val podcastFlow = repository.getPodcasts().cachedIn(viewModelScope)

    private val _podcastListState = MutableStateFlow<PodcastListState>(PodcastListState.Loading)
    val podcastListState = _podcastListState

    init {
        fetchPodcast()
    }

    fun fetchPodcast(){
        _podcastListState.value = PodcastListState.Loading
        repository.getPodcasts()
    }
}