package com.takaobrog.iftest.infrastructure.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.takaobrog.iftest.domain.network.NetworkStatusProvider
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class AndroidNetworkStatusProviderImplTest {

    @Test
    fun activeNetworkがnullならfalse() {
        val context = mock<Context>()
        val cm = mock<ConnectivityManager>()

        whenever(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(null)
        whenever(cm.activeNetwork).thenReturn(null)

        val provider: NetworkStatusProvider = AndroidNetworkStatusProviderImpl(context)

        assertFalse(provider.isOnline())
    }

    @Test
    fun networkCapabilitiesがnullならfalse() {
        val context = mock<Context>()
        val cm = mock<ConnectivityManager>()
        val network = mock<Network>()

        whenever(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(cm)
        whenever(cm.activeNetwork).thenReturn(network)
        whenever(cm.getNetworkCapabilities(network)).thenReturn(null)

        val provider: NetworkStatusProvider = AndroidNetworkStatusProviderImpl(context)

        assertFalse(provider.isOnline())
    }

    @Test
    fun NET_CAPABILITY_INTERNETがあればtrue() {
        val context = mock<Context>()
        val cm = mock<ConnectivityManager>()
        val network = mock<Network>()
        val caps = mock<NetworkCapabilities>()

        whenever(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(cm)
        whenever(cm.activeNetwork).thenReturn(network)
        whenever(cm.getNetworkCapabilities(network)).thenReturn(caps)
        whenever(caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)).thenReturn(true)

        val provider: NetworkStatusProvider = AndroidNetworkStatusProviderImpl(context)

        assertTrue(provider.isOnline())
    }

    @Test
    fun NET_CAPABILITY_INTERNETがなければfalse() {
        val context = mock<Context>()
        val cm = mock<ConnectivityManager>()
        val network = mock<Network>()
        val caps = mock<NetworkCapabilities>()

        whenever(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(cm)
        whenever(cm.activeNetwork).thenReturn(network)
        whenever(cm.getNetworkCapabilities(network)).thenReturn(caps)
        whenever(caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)).thenReturn(false)

        val provider: NetworkStatusProvider = AndroidNetworkStatusProviderImpl(context)

        assertFalse(provider.isOnline())
    }
}