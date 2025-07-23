package dev.sahildesai.thepodcastapp.repository

import androidx.paging.PagingData
import dev.sahildesai.thepodcastapp.model.api.Podcast
import dev.sahildesai.thepodcastapp.model.api.PodcastEpisodeDto
import kotlinx.coroutines.flow.Flow

interface IPodcastRepository {
    fun getPodcasts(): Flow<PagingData<Podcast>>
}