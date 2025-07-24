package dev.sahildesai.thepodcastapp.data.api

import androidx.paging.PagingSource
import dev.sahildesai.thepodcastapp.mockkPodcast
import dev.sahildesai.thepodcastapp.model.api.PodcastResponse
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import retrofit2.Response
import org.junit.Before
import org.junit.Test
import java.io.IOException


class PodcastRemoteSourceTest {

    private val apiService = mockk<PodcastApiService>()
    private lateinit var remoteSource: PodcastRemoteSource

    @Before
    fun setUp() {
        remoteSource = PodcastRemoteSource(apiService)
    }

    @Test
    fun `load returns Page on success`() = runTest {
        val podcasts = listOf(mockkPodcast(1))
        val response = PodcastResponse(
            total = 1,
            podcasts = podcasts,
            hasNext = true,
            pageNumber = 1,
            hasPrevious = false,
            parentId = 0,
            nextPageNumber = 1,
            name = "",
            id = 1,
            listenNotesUrl = "",
            previousPageNumber = 1
        )
        coEvery { apiService.getPodcasts(1) } returns Response.success(response)

        val result = remoteSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        result shouldBe PagingSource.LoadResult.Page(
            data = podcasts,
            prevKey = null,
            nextKey = 2
        )
    }

    @Test
    fun `load returns Error on failure`() = runTest {
        coEvery { apiService.getPodcasts(1) } throws IOException("Network failure")

        val result = remoteSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        (result as PagingSource.LoadResult.Error).throwable.message shouldBe "Network failure"
    }

}