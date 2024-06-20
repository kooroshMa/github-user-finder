package com.example.githubuserfinder.features.feature_repoDetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.githubuserfinder.R
import com.example.githubuserfinder.ui.common.SimpleTopAppBar
import com.example.githubuserfinder.ui.theme.space

@Composable
internal fun RepoDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    avatarUrl: String,
    repoTitle: String,
    repoDescription: String,
    stars: String,
    name: String,
) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(title = stringResource(id = R.string.app_name)) { onBackClick.invoke() }
        },
        content = {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
                    .then(modifier.padding(MaterialTheme.space.small))
            ) {
                Row {
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        painter = rememberAsyncImagePainter(model = avatarUrl),
                        contentDescription = avatarUrl,
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.space.small))

                    Column {

                        Row {
                            Text(
                                text = stringResource(R.string.name),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )

                            Spacer(modifier = Modifier.width(MaterialTheme.space.small))

                            Text(
                                text = name,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }

                        Spacer(modifier = Modifier.height(MaterialTheme.space.small))

                        Row {
                            Text(
                                text = stringResource(R.string.repoTitle),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )

                            Spacer(modifier = Modifier.width(MaterialTheme.space.small))

                            Text(
                                text = repoTitle,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }

                        Spacer(modifier = Modifier.height(MaterialTheme.space.small))

                        Row {
                            Text(
                                text = stringResource(R.string.stars),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )

                            Spacer(modifier = Modifier.width(MaterialTheme.space.small))

                            Text(
                                text = stars,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }

                    }
                }

                Spacer(modifier = Modifier.height(MaterialTheme.space.small))

                Row {
                    Text(
                        text = stringResource(R.string.repoDescription),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.space.small))

                    Text(
                        text = repoDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    )
}