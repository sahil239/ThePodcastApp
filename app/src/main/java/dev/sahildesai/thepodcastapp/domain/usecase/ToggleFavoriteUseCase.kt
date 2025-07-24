package dev.sahildesai.thepodcastapp.domain.usecase

import dev.sahildesai.thepodcastapp.repository.IPodcastRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: IPodcastRepository
): IToggleFavoriteUseCase {
    override suspend fun invoke(podcastId: String, isFavorite: Boolean) {
        return repository.toggleFav(podcastId, isFavorite)
    }
}