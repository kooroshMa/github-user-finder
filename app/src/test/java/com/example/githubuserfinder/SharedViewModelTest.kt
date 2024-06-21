package com.example.githubuserfinder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubuserfinder.features.feature_repoDetail.ui.BadgeState
import com.example.githubuserfinder.features.feature_userDetail.model.UserReposItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class SharedViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SharedViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SharedViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `sumForks should correctly sum forks and update state to Finished`() = runBlocking {
        val repos = listOf(
            UserReposItem(
                forks = 5,
                name = null,
                description = null,
                updatedAt = null,
                stars = null
            ),
            UserReposItem(
                forks = 10,
                name = null,
                description = null,
                updatedAt = null,
                stars = null
            ),
            UserReposItem(
                forks = null,
                name = null,
                description = null,
                updatedAt = null,
                stars = null
            )
        )

        val job = launch { viewModel.sharedState.first { it is BadgeState.Finished } }
        viewModel.sumForks(repos)

        job.join()
        val state = viewModel.sharedState.first()
        Assert.assertTrue(state is BadgeState.Finished)
        if (state is BadgeState.Finished) {
            Assert.assertEquals(15L, state.forks)
        }
    }

    @Test
    fun `stopCalculation should cancel the job and reset state`() = runBlocking {
        val repos = listOf(
            UserReposItem(
                forks = 5,
                name = null,
                description = null,
                updatedAt = null,
                stars = null
            ),
            UserReposItem(
                forks = 10,
                name = null,
                description = null,
                updatedAt = null,
                stars = null
            )
        )

        viewModel.sumForks(repos, 4000L)
        val loadingState = viewModel.sharedState.first { it is BadgeState.Loading }

        viewModel.stopCalculation()
        val state = viewModel.sharedState.first()

        Assert.assertTrue(loadingState is BadgeState.Loading)
        Assert.assertTrue(state is BadgeState.None)
    }
}