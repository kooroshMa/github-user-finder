package com.example.data.source.cloud

import com.example.data.restful.API
import com.example.domain.model.UserDetailModel

class CloudRepository(private val api: API) : BaseCloudRepository {
    override suspend fun getUserDetail(userName: String): UserDetailModel {
        return api.getUserDetail(userName)
    }
}