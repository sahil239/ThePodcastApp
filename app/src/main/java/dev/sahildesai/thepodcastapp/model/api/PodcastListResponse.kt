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


data class PodcastResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("podcasts") val podcasts: List<Podcast>,
    @SerializedName("page_number") val pageNumber: Int,
    @SerializedName("has_previous") val hasPrevious: Boolean,
    @SerializedName("parent_id") val parentId: Int,
    @SerializedName("next_page_number") val nextPageNumber: Int,
    @SerializedName("name") val name: String,
    @SerializedName("has_next") val hasNext: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("listennotes_url") val listenNotesUrl: String,
    @SerializedName("previous_page_number") val previousPageNumber: Int
)

data class Podcast(
    @SerializedName("country") val country: String,
    @SerializedName("update_frequency_hours") val updateFrequencyHours: Int,
    @SerializedName("listen_score_global_rank") val listenScoreGlobalRank: String,
    @SerializedName("itunes_id") val itunesId: Long,
    @SerializedName("explicit_content") val explicitContent: Boolean,
    @SerializedName("audio_length_sec") val audioLengthSec: Int,
    @SerializedName("description") val description: String,
    @SerializedName("language") val language: String,
    @SerializedName("type") val type: String,
    @SerializedName("title") val title: String,
    @SerializedName("is_claimed") val isClaimed: Boolean,
    @SerializedName("rss") val rss: String,
    @SerializedName("extra") val extra: Extra,
    @SerializedName("has_sponsors") val hasSponsors: Boolean,
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("image") val image: String,
    @SerializedName("website") val website: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("earliest_pub_date_ms") val earliestPubDateMs: Long,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("listennotes_url") val listenNotesUrl: String,
    @SerializedName("total_episodes") val totalEpisodes: Int,
    @SerializedName("has_guest_interviews") val hasGuestInterviews: Boolean,
    @SerializedName("looking_for") val lookingFor: LookingFor,
    @SerializedName("latest_episode_id") val latestEpisodeId: String,
    @SerializedName("listen_score") val listenScore: Int,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("latest_pub_date_ms") val latestPubDateMs: Long
)

data class Extra(
    @SerializedName("twitter_handle") val twitterHandle: String,
    @SerializedName("instagram_handle") val instagramHandle: String,
    @SerializedName("url3") val url3: String,
    @SerializedName("url1") val url1: String,
    @SerializedName("amazon_music_url") val amazonMusicUrl: String,
    @SerializedName("url2") val url2: String,
    @SerializedName("facebook_handle") val facebookHandle: String,
    @SerializedName("linkedin_url") val linkedinUrl: String,
    @SerializedName("youtube_url") val youtubeUrl: String,
    @SerializedName("spotify_url") val spotifyUrl: String,
    @SerializedName("wechat_handle") val wechatHandle: String,
    @SerializedName("patreon_handle") val patreonHandle: String
)

data class LookingFor(
    @SerializedName("cross_promotion") val crossPromotion: Boolean,
    @SerializedName("sponsors") val sponsors: Boolean,
    @SerializedName("guests") val guests: Boolean,
    @SerializedName("cohosts") val cohosts: Boolean
)