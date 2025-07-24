package dev.sahildesai.thepodcastapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.sahildesai.thepodcastapp.model.db.PodcastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PodcastDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavourite(favourite: PodcastEntity)

    @Query("delete from favourite_podcast where podcastId = :podcastId")
    suspend fun removeFavourite(podcastId: String)

    @Query("select exists(select 1 from favourite_podcast where podcastId = :podcastId)")
    suspend fun isFavourite(podcastId: String): Boolean

    @Query("SELECT podcastId FROM favourite_podcast")
    fun getFavoriteIds(): Flow<List<String>>
}