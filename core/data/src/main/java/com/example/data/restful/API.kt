package com.example.data.restful

import com.example.domain.model.UserDetailModel
import com.example.domain.model.UserReposModel
import retrofit2.http.GET
import retrofit2.http.Path

interface API{
    @GET("/users/{userId}")
    suspend fun getUserDetail(@Path("userId") username: String): UserDetailModel

    @GET("/users/{userId}/repos")
    suspend fun getUserRepos(@Path("userId") username: String): List<UserReposModel>
}