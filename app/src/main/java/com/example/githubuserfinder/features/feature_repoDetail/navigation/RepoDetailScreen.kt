package com.example.githubuserfinder.features.feature_repoDetail.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.githubuserfinder.features.feature_repoDetail.ui.RepoDetailRoute
import com.example.githubuserfinder.ui.SharedViewModel

const val REPO_DETAIL_ROUTE = "repo_detail"

const val AVATAR_URL = "avatarUrl"
const val REPO_TITLE = "repoTitle"
const val REPO_DESCRIPTION = "repoDescription"
const val STARS = "stars"
const val NAME = "name"

internal fun NavController.navigateRepoDetail(
    navOptions: NavOptions? = null,
    avatarUrl: String?,
    title: String?,
    description: String?,
    stars: String?,
    name: String?
) {

    val encodeAvatarUrl = Uri.encode(avatarUrl)

    navigate(
        route = "$REPO_DETAIL_ROUTE/$encodeAvatarUrl/$title/$description/$stars/$name",
        navOptions = navOptions
    )
}

fun NavGraphBuilder.repoDetailScreen(
    onBackClick: () -> Unit,
    sharedViewModel: SharedViewModel,
) {
    composable(
        route = "$REPO_DETAIL_ROUTE/{$AVATAR_URL}/{$REPO_TITLE}/{$REPO_DESCRIPTION}/{$STARS}/{$NAME}",
        listOf(
            navArgument(AVATAR_URL) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(REPO_TITLE) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(REPO_DESCRIPTION) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(STARS) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(NAME) {
                type = NavType.StringType
                nullable = true
            }
        )
    ) {
        val description = Uri.decode(it.arguments?.getString(REPO_DESCRIPTION))
        val title = it.arguments?.getString(REPO_TITLE)
        val avatarUrl = it.arguments?.getString(AVATAR_URL)
        val stars = it.arguments?.getString(STARS)
        val name = it.arguments?.getString(NAME)

        RepoDetailRoute(
            sharedViewModel = sharedViewModel,
            onBackClick = onBackClick,
            repoDescription = description.orEmpty(),
            repoTitle = title.orEmpty(),
            avatarUrl = Uri.decode(avatarUrl),
            stars = stars.orEmpty(),
            name = name.orEmpty()
        )
    }
}