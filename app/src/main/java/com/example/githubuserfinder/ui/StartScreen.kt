package com.example.githubuserfinder.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val START_PREFIX = "start"
internal const val START_ROUTE = "${START_PREFIX}_route"

fun NavGraphBuilder.startScreen(navController: NavController) {
    composable(route = START_ROUTE) {
        Greeting(START_ROUTE)
    }
}