package dev.sahildesai.thepodcastapp.ui.podcast_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sahildesai.thepodcastapp.model.common.PodcastModel
import dev.sahildesai.thepodcastapp.repository.IPodcastRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastDetailsViewModel @Inject constructor(
    private val repository: IPodcastRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val podcastArg: PodcastModel = checkNotNull(savedStateHandle.toRoute<PodcastModel>())

    private val _podcast = MutableStateFlow(podcastArg)
    val podcast = _podcast

    fun toggleFavorite() {
        val updated = _podcast.value.copy(isFavorite = !_podcast.value.isFavorite)
        _podcast.value = updated

        viewModelScope.launch {
            repository.toggleFav(updated.id, updated.isFavorite)
        }
    }
}