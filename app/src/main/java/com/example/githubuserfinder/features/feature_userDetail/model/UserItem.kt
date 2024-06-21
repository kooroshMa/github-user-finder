package com.example.githubuserfinder.features.feature_userDetail.model

import com.example.domain.model.UserDetailModel

data class UserItem(
    val avatarUrl: String?,
    val userName: String,
)

internal fun UserDetailModel.toUserItem(): UserItem {
    return UserItem(
        avatarUrl = avatarUrl.orEmpty(),
        userName = userName,
    )
}