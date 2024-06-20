package com.example.githubuserfinder.features.feature_userDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.githubuserfinder.features.feature_userDetail.ui.UserDetailRoute

internal const val USER_DETAIL_ROUTE = "detail_route"

fun NavGraphBuilder.userDetailScreen(navController: NavController) {
    composable(route = USER_DETAIL_ROUTE) {
        UserDetailRoute(navController = navController)
    }
}