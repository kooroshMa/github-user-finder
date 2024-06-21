package com.example.data.source.cloud

import com.example.domain.model.UserDetailModel
import com.example.domain.model.UserReposModel

interface BaseCloudRepository {

    suspend fun getUserDetail(userName: String): UserDetailModel

    suspend fun getUserRepos(userName: String): List<UserReposModel>
}