package com.example.githubuserfinder.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable

@Composable
fun TakeHomeApp(
    windowSizeClass: WindowSizeClass,
    appState: TakeHomeAppState = rememberTakeHomeAppState(
        windowSizeClass = windowSizeClass
    ),
) {
    TakeHomeNavHost(
        navController = appState.navController,
        onBackClick = appState::onBackClick
    )
}