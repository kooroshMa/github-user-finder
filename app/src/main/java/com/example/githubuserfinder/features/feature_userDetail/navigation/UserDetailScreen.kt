package com.example.githubuserfinder.features.feature_userDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.githubuserfinder.features.feature_userDetail.ui.UserDetailRoute
import kotlinx.serialization.Serializable


fun NavGraphBuilder.userDetailScreen(
    navController: NavController,
) {
    composable<UserDetail> {
        UserDetailRoute(navController = navController)
    }
}

@Serializable
data object UserDetail