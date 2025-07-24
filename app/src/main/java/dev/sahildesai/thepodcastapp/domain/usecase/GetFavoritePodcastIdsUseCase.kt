package dev.sahildesai.thepodcastapp.domain.usecase

import dev.sahildesai.thepodcastapp.repository.IPodcastRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@OptIn(FlowPreview::class)
class GetFavoritePodcastIdsUseCase @Inject constructor(
    private val repository: IPodcastRepository
): IGetFavoritePodcastIdsUseCase {
    override fun invoke(): Flow<List<String>> = repository.getFavoritePodcastIds()
        .debounce(100)
        .distinctUntilChanged()
}