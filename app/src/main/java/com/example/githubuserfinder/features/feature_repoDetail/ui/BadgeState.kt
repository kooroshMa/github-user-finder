package com.example.githubuserfinder.features.feature_repoDetail.ui

sealed class BadgeState {
    data object None : BadgeState()
    data object Loading : BadgeState()
    data class Finished(val forks: Long) : BadgeState()
}