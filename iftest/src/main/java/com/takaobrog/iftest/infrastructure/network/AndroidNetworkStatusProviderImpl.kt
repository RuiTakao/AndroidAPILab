package com.takaobrog.iftest.infrastructure.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.telephony.TelephonyManager
import android.util.Log
import com.takaobrog.iftest.domain.network.NetworkStatusProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidNetworkStatusProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : NetworkStatusProvider {
    override fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val network = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(network) ?: return false
        return try {
            caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) &&
                    tm.dataNetworkType == TelephonyManager.NETWORK_TYPE_LTE
        } catch (e: SecurityException) {
            e.stackTrace
            false
        }
    }
}