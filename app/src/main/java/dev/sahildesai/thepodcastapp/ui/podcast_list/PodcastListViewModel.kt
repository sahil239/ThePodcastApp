package dev.sahildesai.thepodcastapp.ui.podcast_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sahildesai.thepodcastapp.model.common.PodcastModel
import dev.sahildesai.thepodcastapp.model.toUIModel
import dev.sahildesai.thepodcastapp.repository.IPodcastRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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

    @OptIn(FlowPreview::class)
    val favoriteIdsFlow: Flow<List<String>> = repository.getFavoritePodcastIds()
        .debounce(100)
        .distinctUntilChanged()

    val podcastPagingFlow = repository.getPodcasts().cachedIn(viewModelScope)

    val podcasts: Flow<PagingData<PodcastModel>> = combine(
        podcastPagingFlow,
        favoriteIdsFlow
    ) { pagingData, favoriteIds ->
        pagingData.map { podcast ->
            podcast.toUIModel(isFavourite = favoriteIds.contains(podcast.id))
        }
    }

    fun toggleFavorite(podcastId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.toggleFav(podcastId, isFavorite)
        }
    }
}