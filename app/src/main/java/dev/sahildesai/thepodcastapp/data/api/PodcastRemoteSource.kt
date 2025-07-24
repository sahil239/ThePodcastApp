package dev.sahildesai.thepodcastapp.data.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.sahildesai.thepodcastapp.data.util.ApiResult
import dev.sahildesai.thepodcastapp.data.util.parseAPICall
import dev.sahildesai.thepodcastapp.model.api.Podcast
import javax.inject.Inject

class PodcastRemoteSource @Inject constructor(
    private val podcastApiService: PodcastApiService
): PagingSource<Int, Podcast>() {
    override fun getRefreshKey(state: PagingState<Int, Podcast>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Podcast> {
        val page = params.key ?: 1
        return when(val response = parseAPICall { podcastApiService.getPodcasts(page) }) {
            is ApiResult.Success -> {
                val podcastResponse = response.data

                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (podcastResponse.hasNext) page + 1 else null

                LoadResult.Page(
                    data = podcastResponse.podcasts,
                    prevKey = prevKey,
                    nextKey = nextKey
                )

            }
            is ApiResult.Failure -> {
                Log.d("PodcastRemoteSource", "Error: ${response.errorMessage}")
                LoadResult.Error(Exception(response.errorMessage))
            }
        }
    }
}