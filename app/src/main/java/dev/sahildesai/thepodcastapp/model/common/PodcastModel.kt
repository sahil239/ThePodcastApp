package dev.sahildesai.thepodcastapp.model.common

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PodcastModel(
val id: String,
val title: String,
val thumbnail: String,
val publisher: String,
val description: String,
val isFavorite: Boolean = false
)