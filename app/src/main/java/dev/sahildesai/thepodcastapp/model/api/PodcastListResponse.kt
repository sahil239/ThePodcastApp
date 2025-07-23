package dev.sahildesai.thepodcastapp.model.api

import com.google.gson.annotations.SerializedName


data class PodcastResponseDto(
    @SerializedName("took") val took: Double,
    @SerializedName("count") val count: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("results") val results: List<PodcastEpisodeDto>,
    @SerializedName("next_offset") val nextOffset: Int
)

data class PodcastEpisodeDto(
    @SerializedName("id") val id: String,
    @SerializedName("rss") val rss: String,
    @SerializedName("link") val link: String,
    @SerializedName("audio") val audio: String,
    @SerializedName("image") val image: String,
    @SerializedName("podcast") val podcast: PodcastDto,
    @SerializedName("itunes_id") val itunesId: Long,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("pub_date_ms") val pubDateMs: Long,
    @SerializedName("guid_from_rss") val guidFromRss: String,
    @SerializedName("title_original") val titleOriginal: String,
    @SerializedName("listennotes_url") val listenNotesUrl: String,
    @SerializedName("audio_length_sec") val audioLengthSec: Int,
    @SerializedName("explicit_content") val explicitContent: Boolean,
    @SerializedName("title_highlighted") val titleHighlighted: String,
    @SerializedName("description_original") val descriptionOriginal: String,
    @SerializedName("description_highlighted") val descriptionHighlighted: String,
    @SerializedName("transcripts_highlighted") val transcriptsHighlighted: List<String>
)

data class PodcastDto(
    @SerializedName("id") val id: String,
    @SerializedName("image") val image: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("listen_score") val listenScore: Int,
    @SerializedName("title_original") val titleOriginal: String,
    @SerializedName("listennotes_url") val listenNotesUrl: String,
    @SerializedName("title_highlighted") val titleHighlighted: String,
    @SerializedName("publisher_original") val publisherOriginal: String,
    @SerializedName("publisher_highlighted") val publisherHighlighted: String,
    @SerializedName("listen_score_global_rank") val listenScoreGlobalRank: String
)
