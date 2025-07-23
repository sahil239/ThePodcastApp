package dev.sahildesai.thepodcastapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.sahildesai.thepodcastapp.data.api.PodcastRemoteSource
import dev.sahildesai.thepodcastapp.model.api.Podcast
import dev.sahildesai.thepodcastapp.model.api.PodcastEpisodeDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PodcastRepository @Inject constructor(
    private val podcastRemoteSource: PodcastRemoteSource
): IPodcastRepository {
    override fun getPodcasts(): Flow<PagingData<Podcast>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { podcastRemoteSource }
        ).flow
    }
}