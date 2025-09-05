package com.example.githubuserfinder.features.feature_repoDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.githubuserfinder.features.feature_repoDetail.ui.RepoDetailRoute
import com.example.githubuserfinder.SharedViewModel
import kotlinx.serialization.Serializable

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
        val args = it.toRoute<RepoDetail>()

        RepoDetailRoute(
            sharedViewModel = sharedViewModel,
            onBackClick = onBackClick,
            repoDescription = args.description.orEmpty(),
            repoTitle = args.title.orEmpty(),
            avatarUrl = args.avatarUrl.orEmpty(),
            stars = args.stars.orEmpty(),
            name = args.name.orEmpty()
        )
    }
}