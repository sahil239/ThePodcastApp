package dev.sahildesai.thepodcastapp.repository

import androidx.paging.PagingData
import dev.sahildesai.thepodcastapp.model.api.Podcast
import kotlinx.coroutines.flow.Flow

interface IPodcastRepository {
    fun getPodcasts(): Flow<PagingData<Podcast>>

    fun getFavoritePodcastIds(): Flow<List<String>>

    suspend fun isFavourite(podcastId: String): Boolean

    suspend fun toggleFav(podcastId: String, isFavourite: Boolean)
}