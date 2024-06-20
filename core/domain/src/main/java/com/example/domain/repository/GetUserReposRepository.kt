package com.example.domain.repository

import com.example.domain.model.UserReposModel

interface GetUserReposRepository {

    suspend fun getUserRepos(userName: String): List<UserReposModel>

}