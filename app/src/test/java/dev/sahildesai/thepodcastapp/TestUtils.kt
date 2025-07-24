package dev.sahildesai.thepodcastapp

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import dev.sahildesai.thepodcastapp.model.api.Podcast
import dev.sahildesai.thepodcastapp.model.common.PodcastModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.reflect.KClass

fun mockkPodcast(id: Int): Podcast = Podcast(
    id = "$id",
    title = "Test Show $id",
    thumbnail = "url$id",
    publisher = "Test Publisher $id",
    description = "desc $id",
    country = "",
    updateFrequencyHours = 0,
    listenScoreGlobalRank = "",
    itunesId = 0L,
    explicitContent = false,
    audioLengthSec = 50,
    language = "",
    type = "",
    isClaimed = true,
    rss = "",
    extra = mockk(),
    hasSponsors = false,
    email = "",
    image = "",
    website = "",
    earliestPubDateMs = 1000L,
    genreIds = listOf(1),
    listenNotesUrl = "",
    totalEpisodes = 6,
    hasGuestInterviews = false,
    lookingFor = mockk(),
    latestEpisodeId = "",
    listenScore = 3,
    latestPubDateMs = 10000L,
)


@OptIn(ExperimentalCoroutinesApi::class)
suspend fun TestScope.collectPagingData(
    pagingData: PagingData<PodcastModel>
): List<PodcastModel> {
    val differ = AsyncPagingDataDiffer(
        diffCallback = object : DiffUtil.ItemCallback<PodcastModel>() {
            override fun areItemsTheSame(oldItem: PodcastModel, newItem: PodcastModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PodcastModel, newItem: PodcastModel) =
                oldItem == newItem
        },
        updateCallback = NoopListCallback(),
        mainDispatcher = Dispatchers.Unconfined,
        workerDispatcher = Dispatchers.Unconfined
    )

    differ.submitData(pagingData)
    advanceUntilIdle()
    return differ.snapshot().items
}

class NoopListCallback : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}

class SavedStateHandleRule<T : Any>(
    private val route: T,
    private val clazz: KClass<T>
) : TestWatcher() {

    val savedStateHandleMock: SavedStateHandle = mockk(relaxed = true)

    @Suppress("UNCHECKED_CAST")
    override fun starting(description: Description?) {
        mockkStatic("androidx.navigation.SavedStateHandleKt")
        every { savedStateHandleMock.toRoute(clazz) } returns route
        super.starting(description)
    }

    override fun finished(description: Description?) {
        unmockkStatic("androidx.navigation.SavedStateHandleKt")
        super.finished(description)
    }
}