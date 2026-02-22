package com.takaobrog.iftest.di

import com.takaobrog.iftest.domain.network.NetworkStatusProvider
import com.takaobrog.iftest.infrastructure.network.AndroidNetworkStatusProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun bindNetworkStatusProvider(
        impl: AndroidNetworkStatusProviderImpl
    ): NetworkStatusProvider
}