package dev.sahildesai.thepodcastapp.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.sahildesai.thepodcastapp.data.util.ApiResult
import dev.sahildesai.thepodcastapp.data.util.parseAPICall
import dev.sahildesai.thepodcastapp.model.api.PodcastEpisodeDto

class PodcastRemoteSource(
    private val podcastApiService: PodcastApiService
): PagingSource<Int, PodcastEpisodeDto>() {
    override fun getRefreshKey(state: PagingState<Int, PodcastEpisodeDto>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PodcastEpisodeDto> {
        val page = params.key ?: 1
        return when(val response = parseAPICall { podcastApiService.getPodcasts(page) }) {
            is ApiResult.Success -> {
                val podcastResponse = response.data

                LoadResult.Page(
                    data = podcastResponse.results,
                    prevKey = if (page == 0) null else page - params.loadSize,
                    nextKey = if (podcastResponse.results.isEmpty()) null else podcastResponse.nextOffset
                )

            }
            is ApiResult.Failure -> {
                LoadResult.Error(Exception(response.errorMessage))
            }
        }
    }
}