package com.example.githubuserfinder.di

import com.example.data.repository.GetUserDetailDetailRepoImpl
import com.example.data.repository.GetUserReposRepoImpl
import com.example.domain.repository.GetUserDetailRepository
import com.example.domain.repository.GetUserReposRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBuilder {

    @Binds
    abstract fun bindUserRepo(userRepoImpl: GetUserDetailDetailRepoImpl): GetUserDetailRepository

    @Binds
    abstract fun bindUserReposRepo(userReposRepoImpl: GetUserReposRepoImpl): GetUserReposRepository
}