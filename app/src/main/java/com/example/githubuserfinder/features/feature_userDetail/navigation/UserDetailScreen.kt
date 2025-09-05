package com.example.githubuserfinder.features.feature_userDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.githubuserfinder.features.feature_userDetail.ui.UserDetailRoute
import com.example.githubuserfinder.SharedViewModel
import kotlinx.serialization.Serializable


fun NavGraphBuilder.userDetailScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    composable<UserDetail> {
        UserDetailRoute(navController = navController, sharedViewModel = sharedViewModel)
    }
}

@Serializable
data object UserDetail