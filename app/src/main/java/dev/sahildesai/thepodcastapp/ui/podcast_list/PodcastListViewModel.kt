package dev.sahildesai.thepodcastapp.ui.podcast_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sahildesai.thepodcastapp.domain.usecase.IGetFavoritePodcastIdsUseCase
import dev.sahildesai.thepodcastapp.domain.usecase.IGetPodcastUseCase
import dev.sahildesai.thepodcastapp.model.common.PodcastModel
import dev.sahildesai.thepodcastapp.model.toUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class PodcastListViewModel @Inject constructor(
    getPodcastUseCase: IGetPodcastUseCase,
    getFavoritePodcastIdsUseCase: IGetFavoritePodcastIdsUseCase,
) : ViewModel(){

    val favoriteIdsFlow: Flow<List<String>> = getFavoritePodcastIdsUseCase()

    val podcastPagingFlow = getPodcastUseCase().cachedIn(viewModelScope)

    val podcasts: Flow<PagingData<PodcastModel>> = combine(
        podcastPagingFlow,
        favoriteIdsFlow
    ) { pagingData, favoriteIds ->
        pagingData.map { podcast ->
            podcast.toUIModel(isFavourite = favoriteIds.contains(podcast.id))
        }
    }
}