package com.example.githubuserfinder.features.userDetail.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.githubuserfinder.features.userDetail.UserDetailViewModel

@Composable
internal fun UserDetailRoute(
    viewModel: UserDetailViewModel = hiltViewModel(),
) {

    val user = viewModel.userState.collectAsStateWithLifecycle()
    val isImageSectionVisible = viewModel.isImageSectionVisible.collectAsStateWithLifecycle()

    val userRepos = viewModel.userRepoState.collectAsStateWithLifecycle()
    val isReposVisible = viewModel.isReposVisible.collectAsStateWithLifecycle()

    UserDetailScreen(
        searchUser = viewModel::onSearchUser,
        user = user,
        isImageSectionVisible = isImageSectionVisible,
        userRepos = userRepos,
        isReposVisible = isReposVisible,
        clearStates = viewModel::clearStates,
    )
}