package dev.sahildesai.thepodcastapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.sahildesai.thepodcastapp.data.api.PodcastRemoteSource
import dev.sahildesai.thepodcastapp.data.db.PodcastDao
import dev.sahildesai.thepodcastapp.repository.IPodcastRepository
import dev.sahildesai.thepodcastapp.repository.PodcastRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePodcastRepository(
        podcastRemoteSource: PodcastRemoteSource,
        podcastDao: PodcastDao
    ): IPodcastRepository = PodcastRepository(
        podcastRemoteSource,
        podcastDao
    )
}