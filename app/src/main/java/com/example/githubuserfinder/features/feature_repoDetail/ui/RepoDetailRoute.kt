package com.example.githubuserfinder.features.feature_repoDetail.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.githubuserfinder.SharedViewModel

@Composable
internal fun RepoDetailRoute(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    avatarUrl: String,
    repoTitle: String,
    repoDescription: String,
    stars: String,
    name: String,
) {

    val sharedState = sharedViewModel.sharedState.collectAsStateWithLifecycle()

    RepoDetailScreen(
        onBackClick = onBackClick,
        avatarUrl = avatarUrl,
        repoTitle = repoTitle,
        repoDescription = repoDescription,
        stars = stars,
        name = name,
        sharedState = sharedState,
    )
}