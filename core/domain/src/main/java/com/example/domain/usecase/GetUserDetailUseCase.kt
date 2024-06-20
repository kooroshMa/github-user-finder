package com.example.domain.usecase

import arrow.core.Either
import com.example.domain.mapper.ErrorMapper
import com.example.domain.model.UserDetailModel
import com.example.domain.model.error.NetworkError
import com.example.domain.repository.GetUserDetailRepository
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userDetailRepository: GetUserDetailRepository,
    errorMapper: ErrorMapper
) : BaseUseCase(errorMapper) {

    suspend fun getUserDetail(userName: String): Either<NetworkError, UserDetailModel> {
        return safeApiCall {
            userDetailRepository.getUserDetail(userName)
        }
    }
}