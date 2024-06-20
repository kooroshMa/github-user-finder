package com.example.githubuserfinder.features.feature_repoDetail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.githubuserfinder.R
import com.example.githubuserfinder.ui.common.SimpleTopAppBar

@Composable
internal fun RepoDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(title = stringResource(id = R.string.app_name)) { onBackClick.invoke() }
        },
        content = {
            Text(
                modifier = modifier.padding(it),
                text = "Repo Screen"
            )
        }
    )
}