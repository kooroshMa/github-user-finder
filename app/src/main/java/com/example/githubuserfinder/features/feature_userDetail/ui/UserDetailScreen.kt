package com.example.githubuserfinder.features.feature_userDetail.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.githubuserfinder.R
import com.example.githubuserfinder.ui.theme.space
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.githubuserfinder.features.feature_userDetail.model.UserItem
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.runtime.State
import com.example.githubuserfinder.features.feature_userDetail.model.UserReposItem
import com.example.githubuserfinder.ui.common.SimpleTopAppBar

@Composable
internal fun UserDetailScreen(
    modifier: Modifier = Modifier,
    searchUser: (String) -> Unit,
    user: State<UserItem>,
    isImageSectionVisible: State<MutableTransitionState<Boolean>>,
    userRepos: State<List<UserReposItem>>,
    isReposVisible: State<MutableTransitionState<Boolean>>,
    clearStates: () -> Unit,
) {
    Scaffold(
        topBar = {
            SimpleTopAppBar(title = stringResource(id = R.string.app_name))
        },
        content = {
            UserDetailScreenContent(
                modifier = modifier.padding(it),
                searchUser = searchUser,
                user,
                isImageSectionVisible,
                userRepos,
                isReposVisible,
                clearStates,
            )
        }
    )
}

@Composable
internal fun UserDetailScreenContent(
    modifier: Modifier,
    searchUser: (String) -> Unit,
    userItem: State<UserItem>,
    isImageSectionVisible: State<MutableTransitionState<Boolean>>,
    userRepos: State<List<UserReposItem>>,
    isReposVisible: State<MutableTransitionState<Boolean>>,
    clearStates: () -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.space.small),
    ) {
        Row(Modifier.fillMaxWidth()) {

            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text(text = stringResource(id = R.string.hint_search)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                    searchUser(text)
                }),
                modifier = Modifier
                    .padding(MaterialTheme.space.small)
                    .testTag("search_user")
                    .weight(1f),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                )
            )

            Button(
                onClick = {
                    keyboardController?.hide()
                    clearStates.invoke()
                    searchUser(text)
                },
                shape = RoundedCornerShape(MaterialTheme.space.xSmall),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(R.string.search),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.space.small))

        ImageAndTitleSection(userItem, isImageSectionVisible)

        Spacer(modifier = Modifier.height(MaterialTheme.space.small))

        AnimatedVisibility(
            visibleState = isReposVisible.value,
            enter = slideInVertically(
                initialOffsetY = { it / 5 },
                animationSpec = tween(durationMillis = 500)
            ) + fadeIn(animationSpec = tween(durationMillis = 1000)),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("list_item"),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.space.sMedium),
            ) {
                items(userRepos.value, key = {
                    it.hashCode()
                }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(MaterialTheme.space.small)
                    ) {
                        Column(Modifier.padding(MaterialTheme.space.medium)) {

                            Text(
                                text = it.name.orEmpty(),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .fillMaxSize()
                            )

                            Spacer(Modifier.height(MaterialTheme.space.medium))

                            Text(
                                text = it.description.orEmpty(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageAndTitleSection(
    userItem: State<UserItem>,
    isImageSectionVisible: State<MutableTransitionState<Boolean>>
) {
    AnimatedVisibility(
        visibleState = isImageSectionVisible.value,
        enter = slideInVertically(
            initialOffsetY = { it / 5 },
            animationSpec = tween(durationMillis = 500)
        ) + fadeIn(animationSpec = tween(durationMillis = 1000))
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                modifier = Modifier
                    .size(100.dp),
                painter = rememberAsyncImagePainter(model = userItem.value.avatarUrl),
                contentDescription = userItem.value.avatarUrl,
            )

            Spacer(modifier = Modifier.height(MaterialTheme.space.small))

            Text(
                text = userItem.value.userName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}