package dev.sahildesai.thepodcastapp.ui.podcast_list

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import dev.sahildesai.thepodcastapp.collectPagingData
import dev.sahildesai.thepodcastapp.domain.usecase.IGetFavoritePodcastIdsUseCase
import dev.sahildesai.thepodcastapp.domain.usecase.IGetPodcastsUseCase
import dev.sahildesai.thepodcastapp.mockkPodcast
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalPagingApi
class PodcastListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getPodcastsUseCase: IGetPodcastsUseCase
    private lateinit var getFavoritePodcastIdsUseCase: IGetFavoritePodcastIdsUseCase
    private lateinit var viewModel: PodcastListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getPodcastsUseCase = mockk()
        getFavoritePodcastIdsUseCase = mockk()
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    private fun setupViewModel() {
        viewModel = PodcastListViewModel(
            getPodcastsUseCase = getPodcastsUseCase,
            getFavoritePodcastIdsUseCase = getFavoritePodcastIdsUseCase
        )
    }

    @Test
    fun `podcasts emits UI models with correct favorite state`() = runTest (testDispatcher) {
        val podcastList = listOf(mockkPodcast(1), mockkPodcast(2))
        val pagingData = PagingData.from(podcastList)

        coEvery { getPodcastsUseCase() } returns flowOf(pagingData)
        coEvery { getFavoritePodcastIdsUseCase() } returns flowOf(listOf("1"))

        setupViewModel()

        val emitted = viewModel.podcasts.first()
        val result = collectPagingData(emitted)

        result[0].isFavorite shouldBe true
        result[1].isFavorite shouldBe false
    }


}