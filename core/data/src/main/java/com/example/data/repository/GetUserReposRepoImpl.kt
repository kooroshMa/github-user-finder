package com.example.data.repository

import com.example.data.di.Cloud
import com.example.data.source.cloud.BaseCloudRepository
import com.example.domain.model.UserReposModel
import com.example.domain.repository.GetUserReposRepository
import javax.inject.Inject

class GetUserReposRepoImpl @Inject constructor(
    @Cloud private val baseCloudRepository: BaseCloudRepository
) : GetUserReposRepository {
    override suspend fun getUserRepos(userName: String): List<UserReposModel> {
        return baseCloudRepository.getUserRepos(userName)
    }
}