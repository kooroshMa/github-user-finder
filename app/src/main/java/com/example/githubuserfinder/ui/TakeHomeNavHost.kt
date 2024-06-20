package com.example.githubuserfinder.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.githubuserfinder.features.feature_repoDetail.navigation.repoDetailScreen
import com.example.githubuserfinder.features.feature_userDetail.navigation.USER_DETAIL_ROUTE
import com.example.githubuserfinder.features.feature_userDetail.navigation.userDetailScreen

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TakeHomeNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = USER_DETAIL_ROUTE,
    sharedViewModel: SharedViewModel = hiltViewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier
            .semantics {
                testTagsAsResourceId = true
            }
            .then(modifier.fillMaxSize())
    ) {
        userDetailScreen(navController, sharedViewModel)
        repoDetailScreen(onBackClick, sharedViewModel)
    }
}