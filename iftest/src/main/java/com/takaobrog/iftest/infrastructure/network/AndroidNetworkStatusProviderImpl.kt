package com.takaobrog.iftest.infrastructure.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.takaobrog.iftest.domain.network.NetworkStatusProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidNetworkStatusProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : NetworkStatusProvider {
    override fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager ?: return false
        val network = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(network) ?: return false
        return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}