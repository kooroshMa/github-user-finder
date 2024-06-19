package com.example.githubuserfinder.ui.theme

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Day Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class ThemePreview

@Composable
fun TakeHomePreviewTheme(content: @Composable () -> Unit) {
    TakeHomeTheme {
        content()
    }
}