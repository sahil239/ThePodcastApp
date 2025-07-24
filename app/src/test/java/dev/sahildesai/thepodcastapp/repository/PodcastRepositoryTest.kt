package dev.sahildesai.thepodcastapp.repository

import app.cash.turbine.test
import dev.sahildesai.thepodcastapp.data.api.PodcastRemoteSource
import dev.sahildesai.thepodcastapp.data.db.PodcastDao
import dev.sahildesai.thepodcastapp.model.db.PodcastEntity
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class PodcastRepositoryTest {

    private val podcastRemoteSource = mockk<PodcastRemoteSource>()
    private val podcastDao = mockk<PodcastDao>()

    private lateinit var repository: PodcastRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = PodcastRepository(podcastRemoteSource, podcastDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getFavoritePodcastIds returns correct flow`() = runTest {
        val expectedIds = listOf("1", "2", "3")
        every { podcastDao.getFavoriteIds() } returns flowOf(expectedIds)

        repository.getFavoritePodcastIds().test {
            awaitItem() shouldBe expectedIds
            cancelAndConsumeRemainingEvents()
        }
    }


    @Test
    fun `toggleFav adds to favorites when isFavourite is true`() = runTest {
        coEvery { podcastDao.addFavourite(any()) } just Runs

        repository.toggleFav("1", true)

        coVerify { podcastDao.addFavourite(PodcastEntity("1")) }
    }

    @Test
    fun `toggleFav removes from favorites when isFavourite is false`() = runTest {
        coEvery { podcastDao.removeFavourite("2") } just Runs

        repository.toggleFav("2", false)

        coVerify { podcastDao.removeFavourite("2") }
    }
}