package com.takaobrog.iftest.domain.network

interface NetworkStatusProvider {
    fun isOnline(): Boolean
}