package com.example.githubuserfinder.features.feature_userDetail

import androidx.compose.animation.core.MutableTransitionState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.UserDetailModel
import com.example.domain.model.UserReposModel
import com.example.domain.model.error.NetworkError
import com.example.domain.usecase.GetUserDetailUseCase
import com.example.domain.usecase.GetUserReposRepoUseCase
import com.example.githubuserfinder.features.feature_userDetail.model.UserItem
import com.example.githubuserfinder.features.feature_userDetail.model.UserReposItem
import com.example.githubuserfinder.features.feature_userDetail.model.toUserItem
import com.example.githubuserfinder.features.feature_userDetail.model.toUserReposItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val getUserReposRepoUseCase: GetUserReposRepoUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow(UserItem(userName = "", avatarUrl = ""))
    val userState = _userState.asStateFlow()

    private val _isImageSectionVisible = MutableStateFlow(MutableTransitionState(false))
    val isImageSectionVisible = _isImageSectionVisible.asStateFlow()

    private val _userRepoState = MutableStateFlow(emptyList<UserReposItem>())
    val userRepoState = _userRepoState.asStateFlow()

    private val _isReposVisible = MutableStateFlow(MutableTransitionState(false))
    val isReposVisible = _isReposVisible.asStateFlow()

    fun onSearchUser(userName: String) {
        if (userName.isBlank()) return
        viewModelScope.launch {
            getUserDetailUseCase.getUserDetail(userName).fold(
                ifRight = ::onSuccessResponse,
                ifLeft = ::onErrorResponse
            )

            getUserReposRepoUseCase.getUserRepos(userName).fold(
                ifRight = ::onSuccessResponse,
                ifLeft = ::onErrorResponse
            )
        }
    }

    private fun onSuccessResponse(users: UserDetailModel) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                _userState.value = users.toUserItem()
            }
            _isImageSectionVisible.value.targetState = true
        }
    }

    private fun onSuccessResponse(repos: List<UserReposModel>) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                _userRepoState.value = repos.map { it.toUserReposItem() }
            }
            delay(500L)
            _isReposVisible.value.targetState = true
        }
    }

    private fun onErrorResponse(error: NetworkError) {
        //handle error based on the project.
    }

    fun clearStates(){
        _userRepoState.value = emptyList()
        _userState.value = UserItem(null,"")
    }
}