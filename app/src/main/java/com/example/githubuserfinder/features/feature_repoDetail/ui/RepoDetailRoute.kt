package com.example.githubuserfinder.features.feature_repoDetail.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.githubuserfinder.SharedViewModel
import com.example.githubuserfinder.ui.sharedHiltViewModel

@Composable
internal fun RepoDetailRoute(
    navController: NavController,
    sharedViewModel: SharedViewModel = sharedHiltViewModel(navController),
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