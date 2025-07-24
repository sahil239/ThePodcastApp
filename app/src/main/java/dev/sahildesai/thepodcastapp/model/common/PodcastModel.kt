package dev.sahildesai.thepodcastapp.model.common

import kotlinx.serialization.Serializable

@Serializable
data class PodcastModel(
val id: String,
val title: String,
val thumbnail: String,
val publisher: String,
val isFavorite: Boolean = false
)