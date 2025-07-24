package dev.sahildesai.thepodcastapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.sahildesai.thepodcastapp.model.db.PodcastEntity

@Database(entities = [PodcastEntity::class], version = 1)
abstract class PodcastDatabase : RoomDatabase() {
    abstract fun podcastDao(): PodcastDao
}