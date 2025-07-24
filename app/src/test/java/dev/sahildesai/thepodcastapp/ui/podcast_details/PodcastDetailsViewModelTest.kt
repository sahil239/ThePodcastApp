package dev.sahildesai.thepodcastapp.ui.podcast_details

import dev.sahildesai.thepodcastapp.SavedStateHandleRule
import dev.sahildesai.thepodcastapp.domain.usecase.IToggleFavoriteUseCase
import dev.sahildesai.thepodcastapp.mockkPodcast
import dev.sahildesai.thepodcastapp.model.common.PodcastModel
import dev.sahildesai.thepodcastapp.model.toUIModel
import io.kotest.matchers.shouldBe
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PodcastDetailsViewModelTest {
    @get:Rule
    val savedStateHandleRule = SavedStateHandleRule<PodcastModel>(
        mockkPodcast(1).toUIModel(),
        clazz = PodcastModel::class
    )

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var toggleFavoriteUseCase: IToggleFavoriteUseCase

    private lateinit var viewModel: PodcastDetailsViewModel

    private val fakePodcast = mockkPodcast(1).toUIModel()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        toggleFavoriteUseCase = mockk(relaxed = true)
        setupViewModel()
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    private fun setupViewModel() {
        viewModel = PodcastDetailsViewModel(
            savedStateHandle = savedStateHandleRule.savedStateHandleMock,
            toggleFavoriteUseCase = toggleFavoriteUseCase
        )
    }

    @Test
    fun `initial podcast is loaded from saved state`() = runTest {
        viewModel.podcast.value shouldBe fakePodcast
    }

    @Test
    fun `toggleFavorite updates state and invokes use case`() = runTest {
        viewModel.podcast.value.isFavorite shouldBe false
        viewModel.toggleFavorite()
        advanceUntilIdle()
        coVerify { toggleFavoriteUseCase("1", true) }
        viewModel.podcast.value.isFavorite shouldBe true
    }
}