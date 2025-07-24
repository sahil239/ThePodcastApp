package dev.sahildesai.thepodcastapp.domain.usecase

import androidx.paging.PagingData
import dev.sahildesai.thepodcastapp.model.api.Podcast
import dev.sahildesai.thepodcastapp.repository.IPodcastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPodcastsUseCase @Inject constructor(
    private val repository: IPodcastRepository
): IGetPodcastsUseCase {
    override fun invoke(): Flow<PagingData<Podcast>> {
        return repository.getPodcasts()
    }
}