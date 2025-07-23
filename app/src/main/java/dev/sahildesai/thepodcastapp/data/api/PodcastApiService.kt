package dev.sahildesai.thepodcastapp.data.api

import dev.sahildesai.thepodcastapp.model.api.PodcastResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastApiService {

    @GET("best_podcasts")
    suspend fun getPodcasts(@Query("page") page: Int): Response<PodcastResponseDto>

}