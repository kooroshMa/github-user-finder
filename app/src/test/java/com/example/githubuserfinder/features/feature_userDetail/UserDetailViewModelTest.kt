package com.example.githubuserfinder.features.feature_userDetail

import arrow.core.left
import arrow.core.right
import com.example.domain.model.UserDetailModel
import com.example.domain.model.UserReposModel
import com.example.domain.model.error.NetworkError
import com.example.domain.usecase.GetUserDetailUseCase
import com.example.domain.usecase.GetUserReposRepoUseCase
import com.example.githubuserfinder.features.feature_userDetail.model.UserItem
import com.example.githubuserfinder.util.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.any

@ExperimentalCoroutinesApi
class UserDetailViewModelTest {

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    private var getUserDetailUseCase: GetUserDetailUseCase = mock()

    private var getUserReposRepoUseCase: GetUserReposRepoUseCase = mock()

    private lateinit var viewModel: UserDetailViewModel

    @Before
    fun setup() {
        viewModel = UserDetailViewModel(getUserDetailUseCase, getUserReposRepoUseCase)
    }

    @Test
    fun `onSearchUser error logs error and does not update state`() = runBlocking {
        val userName = "testUser"
        val networkError = NetworkError.NotDefined(Throwable())

        whenever(getUserDetailUseCase.getUserDetail(userName)).thenReturn(networkError.left())
        whenever(getUserReposRepoUseCase.getUserRepos(userName)).thenReturn(networkError.left())

        viewModel.onSearchUser(userName)

        verify(getUserDetailUseCase).getUserDetail(userName)
        verify(getUserReposRepoUseCase).getUserRepos(userName)

        assertEquals(UserItem("", ""), viewModel.userState.value)
        assertFalse(viewModel.isImageSectionVisible.value.targetState)
        assertTrue(viewModel.userRepoState.value.isEmpty())
        assertFalse(viewModel.isReposVisible.value.targetState)
    }


    @Test
    fun `getUserDetail update the UI state correctly when success`() = runTest {
        val userDetailModel = UserDetailModel("Koorosh", "")

        whenever(getUserDetailUseCase.getUserDetail(userDetailModel.userName)).thenReturn(userDetailModel.right())
        whenever(getUserReposRepoUseCase.getUserRepos(any()))
            .thenReturn(emptyList<UserReposModel>().right())

        viewModel.onSearchUser(userDetailModel.userName)

        advanceUntilIdle()

        assertEquals(userDetailModel.userName, viewModel.userState.value.userName)
    }


    @Test
    fun `clearStates resets userRepoState and userState`() = runBlocking {
        viewModel.clearStates()

        assertTrue(viewModel.userRepoState.value.isEmpty())
        assertEquals(UserItem(null, ""), viewModel.userState.value)
    }
}