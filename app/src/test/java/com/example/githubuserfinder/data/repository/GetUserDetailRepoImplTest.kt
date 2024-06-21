package com.example.githubuserfinder.data.repository

import com.example.data.repository.GetUserDetailRepoImpl
import com.example.data.source.cloud.BaseCloudRepository
import com.example.domain.model.UserDetailModel
import com.example.domain.repository.GetUserDetailRepository
import com.example.githubuserfinder.util.CoroutinesTestRule
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.hamcrest.CoreMatchers.`is`


@RunWith(MockitoJUnitRunner::class)
class GetUserDetailRepoImplTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val baseCloudRepository: BaseCloudRepository = mock()
    private lateinit var getUsersRepository: GetUserDetailRepository

    @Before
    fun setUp() {
        getUsersRepository = GetUserDetailRepoImpl(baseCloudRepository)
    }

    @Test
    fun `the response should be get successfully when getUsersRepository is called`() = runTest {
        // Given
        val userName = "test"
        val avatarUrl = "testUrl"
        val response = UserDetailModel(userName, avatarUrl)
        whenever(baseCloudRepository.getUserDetail(userName)) doReturn response

        // When
        val result = getUsersRepository.getUserDetail(userName)

        // Then
        MatcherAssert.assertThat(result, `is`(response))
        verify(baseCloudRepository).getUserDetail(userName)
    }
}