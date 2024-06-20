package com.example.data.source.cloud

import com.example.domain.model.UserDetailModel

class CloudMockRepository : BaseCloudRepository {
    override suspend fun getUserDetail(userName: String): UserDetailModel {
        TODO("Not yet implemented")
    }
}