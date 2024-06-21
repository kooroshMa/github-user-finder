package com.example.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailModel(
    @SerialName("login") val userName: String,
    @SerialName("avatar_url") val avatarUrl: String?,
)