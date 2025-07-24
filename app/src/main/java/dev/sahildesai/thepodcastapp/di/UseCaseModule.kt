package dev.sahildesai.thepodcastapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.sahildesai.thepodcastapp.domain.usecase.GetFavoritePodcastIdsUseCase
import dev.sahildesai.thepodcastapp.domain.usecase.GetPodcastUseCase
import dev.sahildesai.thepodcastapp.domain.usecase.IGetFavoritePodcastIdsUseCase
import dev.sahildesai.thepodcastapp.domain.usecase.IGetPodcastUseCase
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
    ): IGetPodcastUseCase = GetPodcastUseCase(repository)

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