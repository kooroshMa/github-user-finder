package com.example.domain.usecase

import arrow.core.Either
import com.example.domain.mapper.ErrorMapper
import com.example.domain.model.UserReposModel
import com.example.domain.model.error.NetworkError
import com.example.domain.repository.GetUserReposRepository
import javax.inject.Inject

class GetUserReposRepoUseCase @Inject constructor(
    private val userReposRepository: GetUserReposRepository,
    errorMapper: ErrorMapper
) : BaseUseCase(errorMapper) {

    suspend fun getUserRepos(userName: String): Either<NetworkError, List<UserReposModel>> {
        return safeApiCall {
            userReposRepository.getUserRepos(userName)
        }
    }
}