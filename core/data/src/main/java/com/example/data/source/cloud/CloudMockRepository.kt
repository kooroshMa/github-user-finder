package com.example.data.source.cloud

import com.example.domain.model.UserDetailModel
import com.example.domain.model.UserReposModel

class CloudMockRepository : BaseCloudRepository {
    override suspend fun getUserDetail(userName: String): UserDetailModel {
        TODO("Not yet implemented")
    }

    override suspend fun getUserRepos(userName: String): List<UserReposModel> {
        TODO("Not yet implemented")
    }
}