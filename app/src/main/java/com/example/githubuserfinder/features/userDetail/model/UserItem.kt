package com.example.githubuserfinder.features.userDetail.model

import com.example.domain.model.UserDetailModel

data class UserItem(
    val avatarUrl: String,
    val userName: String,
    val userId: Long,
)

internal fun UserDetailModel.toUserItem(): UserItem {
    return UserItem(
        userId = userId,
        avatarUrl = avatarUrl.orEmpty(),
        userName = userName,
    )
}