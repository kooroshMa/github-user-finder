package com.example.githubuserfinder.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberTakeHomeAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): TakeHomeAppState {
    return remember(navController, coroutineScope, windowSizeClass) {
        TakeHomeAppState(navController)
    }
}

@Stable
class TakeHomeAppState(val navController: NavHostController) {

    fun onBackClick() {
        navController.navigateUp()
    }
}