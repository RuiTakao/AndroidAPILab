package com.takaobrog.iftest.di

import com.takaobrog.iftest.domain.network.NetworkStatusProvider
import com.takaobrog.iftest.fake.FakeNetworkStatusProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class] // ← 本番を置換
)
abstract class FakeNetworkModule {
    @Binds
    @Singleton
    abstract fun bindNetworkStatusProvider(
        impl: FakeNetworkStatusProvider
    ): NetworkStatusProvider
}