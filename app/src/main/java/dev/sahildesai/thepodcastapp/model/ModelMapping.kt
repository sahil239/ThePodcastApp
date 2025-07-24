package dev.sahildesai.thepodcastapp.model

import dev.sahildesai.thepodcastapp.model.api.Podcast
import dev.sahildesai.thepodcastapp.model.common.PodcastModel

fun Podcast.toUIModel(isFavourite: Boolean = false) = PodcastModel(
    id = id,
    title = title,
    thumbnail = thumbnail,
    publisher = publisher,
    isFavorite = isFavourite
)