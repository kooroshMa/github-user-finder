package com.example.domain.repository

import com.example.domain.model.UserDetailModel

interface GetUserDetailRepository {

    suspend fun getUserDetail(userName: String): UserDetailModel
}