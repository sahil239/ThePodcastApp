package dev.sahildesai.thepodcastapp.domain.usecase

import androidx.paging.PagingData
import dev.sahildesai.thepodcastapp.model.api.Podcast
import dev.sahildesai.thepodcastapp.repository.IPodcastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPodcastUseCase @Inject constructor(
    private val repository: IPodcastRepository
): IGetPodcastUseCase {
    override fun invoke(): Flow<PagingData<Podcast>> {
        return repository.getPodcasts()
    }
}