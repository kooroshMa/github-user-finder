package com.example.githubuserfinder.features.feature_userDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.githubuserfinder.features.feature_userDetail.ui.UserDetailRoute

internal const val START_PREFIX = "start"
internal const val START_ROUTE = "${START_PREFIX}_route"

fun NavGraphBuilder.userDetailScreen(navController: NavController) {
    composable(route = START_ROUTE) {
        UserDetailRoute()
    }
}