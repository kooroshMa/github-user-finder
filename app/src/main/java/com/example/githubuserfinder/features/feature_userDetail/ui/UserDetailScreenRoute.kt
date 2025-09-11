package com.example.githubuserfinder.features.feature_userDetail.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.githubuserfinder.features.feature_userDetail.UserDetailViewModel
import com.example.githubuserfinder.SharedViewModel
import com.example.githubuserfinder.ui.sharedHiltViewModel

@Composable
internal fun UserDetailRoute(
    navController: NavController,
    viewModel: UserDetailViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = sharedHiltViewModel(navController),
) {

    val user = viewModel.userState.collectAsStateWithLifecycle()
    val isImageSectionVisible = viewModel.isImageSectionVisible.collectAsStateWithLifecycle()

    val userRepos = viewModel.userRepoState.collectAsStateWithLifecycle()
    val isReposVisible = viewModel.isReposVisible.collectAsStateWithLifecycle()

    val stopCalculation = sharedViewModel::stopCalculation

    UserDetailScreen(
        searchUser = viewModel::onSearchUser,
        user = user,
        isImageSectionVisible = isImageSectionVisible,
        userRepos = userRepos,
        isReposVisible = isReposVisible,
        clearStates = viewModel::clearStates,
        navController = navController,
        sumForks = sharedViewModel::sumForks,
        stopCalculation = stopCalculation,
    )
}