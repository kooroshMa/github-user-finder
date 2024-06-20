package com.example.data.source.cloud

import com.example.domain.model.UserDetailModel

interface BaseCloudRepository {

    suspend fun getUserDetail(userName: String): UserDetailModel
}