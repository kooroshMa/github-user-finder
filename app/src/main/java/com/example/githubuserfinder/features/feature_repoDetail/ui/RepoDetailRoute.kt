package com.example.githubuserfinder.features.feature_repoDetail.ui

import androidx.compose.runtime.Composable


@Composable
internal fun RepoDetailRoute(
    onBackClick: () -> Unit,
    avatarUrl: String,
    repoTitle: String,
    repoDescription: String,
    stars: String,
    name: String,
) {
    RepoDetailScreen(onBackClick = onBackClick)
}