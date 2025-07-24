package dev.sahildesai.thepodcastapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.sahildesai.thepodcastapp.data.db.PodcastDao
import dev.sahildesai.thepodcastapp.data.db.PodcastDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): PodcastDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            PodcastDatabase::class.java,
            name = "podcast.db"
        ).build()

    @Singleton
    @Provides
    fun providesPodcastDao(podcastDatabase: PodcastDatabase): PodcastDao = podcastDatabase.podcastDao()

}