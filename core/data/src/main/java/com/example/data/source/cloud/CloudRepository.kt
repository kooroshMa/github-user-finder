package com.example.data.source.cloud

import com.example.data.restful.API
import com.example.domain.model.UserDetailModel
import com.example.domain.model.UserReposModel

class CloudRepository(private val api: API) : BaseCloudRepository {
    override suspend fun getUserDetail(userName: String): UserDetailModel {
        return api.getUserDetail(userName)
    }

    override suspend fun getUserRepos(userName: String): List<UserReposModel> {
        return api.getUserRepos(userName)
    }
}