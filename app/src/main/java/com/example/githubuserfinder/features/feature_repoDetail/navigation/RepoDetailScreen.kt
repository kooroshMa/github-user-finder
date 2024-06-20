package com.example.githubuserfinder.features.feature_repoDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.githubuserfinder.features.feature_repoDetail.ui.RepoDetailRoute

const val REPO_DETAIL_ROUTE = "repo_detail"

internal fun NavController.navigateRepoDetail(
    navOptions: NavOptions? = null
) {
    navigate(route = REPO_DETAIL_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.repoDetailScreen(onBackClick: () -> Unit) {
    composable(route = REPO_DETAIL_ROUTE) {
        RepoDetailRoute(onBackClick)
    }
}