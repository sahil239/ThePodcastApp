package dev.sahildesai.thepodcastapp.domain.usecase

import androidx.paging.PagingData
import dev.sahildesai.thepodcastapp.model.api.Podcast
import kotlinx.coroutines.flow.Flow

fun interface IGetPodcastsUseCase: () -> Flow<PagingData<Podcast>>