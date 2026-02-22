package com.takaobrog.iftest.fake

import com.takaobrog.iftest.domain.network.NetworkStatusProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeNetworkStatusProvider @Inject constructor() : NetworkStatusProvider {
    @Volatile
    var online: Boolean = true

    override fun isOnline(): Boolean = online
}