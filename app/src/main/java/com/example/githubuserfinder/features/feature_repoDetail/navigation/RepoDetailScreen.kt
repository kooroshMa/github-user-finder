package com.example.githubuserfinder.features.feature_repoDetail.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.githubuserfinder.features.feature_repoDetail.ui.RepoDetailRoute
import com.example.githubuserfinder.SharedViewModel
import kotlinx.serialization.Serializable

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

    navigate(
        RepoDetail(
            avatarUrl = avatarUrl,
            title = title,
            description = description,
            stars = stars,
            name = name,
        ),
        navOptions = navOptions
    )
}

@Serializable
data class RepoDetail(
    val avatarUrl: String?,
    val title: String?,
    val description: String?,
    val stars: String?,
    val name: String?,
)

fun NavGraphBuilder.repoDetailScreen(
    onBackClick: () -> Unit,
    sharedViewModel: SharedViewModel,
) {
    composable<RepoDetail> {
        val description = Uri.decode(it.arguments?.getString(REPO_DESCRIPTION))
        val title = it.arguments?.getString(REPO_TITLE)
        val avatarUrl = Uri.decode(it.arguments?.getString(AVATAR_URL))
        val stars = it.arguments?.getString(STARS)
        val name = it.arguments?.getString(NAME)

        RepoDetailRoute(
            sharedViewModel = sharedViewModel,
            onBackClick = onBackClick,
            repoDescription = description.orEmpty(),
            repoTitle = title.orEmpty(),
            avatarUrl = avatarUrl.orEmpty(),
            stars = stars.orEmpty(),
            name = name.orEmpty()
        )
    }
}