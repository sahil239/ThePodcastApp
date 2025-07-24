package dev.sahildesai.thepodcastapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dev.sahildesai.thepodcastapp.data.api.PodcastRemoteSource
import dev.sahildesai.thepodcastapp.data.db.PodcastDao
import dev.sahildesai.thepodcastapp.model.api.Podcast
import dev.sahildesai.thepodcastapp.model.common.PodcastModel
import dev.sahildesai.thepodcastapp.model.db.PodcastEntity
import dev.sahildesai.thepodcastapp.model.toUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class PodcastRepository @Inject constructor(
    private val podcastRemoteSource: PodcastRemoteSource,
    private val podcastDao: PodcastDao
): IPodcastRepository {
    override fun getPodcasts(): Flow<PagingData<Podcast>> {
        val pagingFlow = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { podcastRemoteSource }
        ).flow

        return pagingFlow
    }

    override fun getFavoritePodcastIds(): Flow<List<String>> = podcastDao.getFavoriteIds()

    override suspend fun isFavourite(podcastId: String): Boolean {
        return podcastDao.isFavourite(podcastId)
    }

    override suspend fun toggleFav(podcastId: String, isFavourite: Boolean) {
        if(isFavourite) {
            podcastDao.addFavourite(PodcastEntity(podcastId))
        }else {
            podcastDao.removeFavourite(podcastId)
        }
    }
}