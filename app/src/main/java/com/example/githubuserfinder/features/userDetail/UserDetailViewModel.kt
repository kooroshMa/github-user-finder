package com.example.githubuserfinder.features.userDetail

import android.util.Log
import androidx.compose.animation.core.MutableTransitionState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.UserDetailModel
import com.example.domain.model.error.NetworkError
import com.example.domain.usecase.GetUserDetailUseCase
import com.example.githubuserfinder.features.userDetail.model.UserItem
import com.example.githubuserfinder.features.userDetail.model.toUserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    private val _usersState = MutableStateFlow(UserItem(userId = 0, userName = "", avatarUrl = ""))
    val usersState = _usersState.asStateFlow()

    private val _isImageSectionVisible = MutableStateFlow(MutableTransitionState(false))
    val isImageSectionVisible = _isImageSectionVisible.asStateFlow()

    fun onSearchUser(userName: String) {
        if (userName.isBlank()) return
        viewModelScope.launch {
            getUserDetailUseCase.getUserDetail(userName).fold(
                ifRight = ::onSuccessResponse,
                ifLeft = ::onErrorResponse
            )
        }
    }

    private fun onSuccessResponse(users: UserDetailModel) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                _usersState.value = users.toUserItem()
                _isImageSectionVisible.value.targetState = true
            }
        }
    }

    private fun onErrorResponse(error: NetworkError) {
        Log.d("error", error.toString())
    }
}