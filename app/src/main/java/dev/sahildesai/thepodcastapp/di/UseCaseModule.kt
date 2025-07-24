package dev.sahildesai.thepodcastapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.sahildesai.thepodcastapp.domain.usecase.GetFavoritePodcastIdsUseCase
import dev.sahildesai.thepodcastapp.domain.usecase.GetPodcastsUseCase
import dev.sahildesai.thepodcastapp.domain.usecase.IGetFavoritePodcastIdsUseCase
import dev.sahildesai.thepodcastapp.domain.usecase.IGetPodcastsUseCase
import dev.sahildesai.thepodcastapp.domain.usecase.IToggleFavoriteUseCase
import dev.sahildesai.thepodcastapp.domain.usecase.ToggleFavoriteUseCase
import dev.sahildesai.thepodcastapp.repository.IPodcastRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetPodcastsUseCase(
        repository: IPodcastRepository
    ): IGetPodcastsUseCase = GetPodcastsUseCase(repository)

    @Singleton
    @Provides
    fun provideGetFavoritePodcastIdsUseCase(
        repository: IPodcastRepository
    ): IGetFavoritePodcastIdsUseCase = GetFavoritePodcastIdsUseCase(repository)

    @Singleton
    @Provides
    fun provideToggleFavoriteUseCase(
        repository: IPodcastRepository
    ): IToggleFavoriteUseCase = ToggleFavoriteUseCase(repository)
}