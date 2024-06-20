package com.example.githubuserfinder.features.feature_userDetail.model

import com.example.domain.model.UserReposModel

data class UserReposItem(
    val name: String?,
    val description: String?,
    val updatedAt:String?,
    val stars: Int?,
    val forks: Int?,
)

internal fun UserReposModel.toUserReposItem(): UserReposItem {
    return UserReposItem(
        description = description,
        name = name,
        updatedAt = updatedAt,
        stars = stars,
        forks = forks,
    )
}