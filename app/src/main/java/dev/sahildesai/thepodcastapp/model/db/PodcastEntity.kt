package dev.sahildesai.thepodcastapp.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_podcast")
data class PodcastEntity(
    @PrimaryKey
    @ColumnInfo (name = "podcastId")
    val podcastId: String
)