package com.takaobrog.iftest.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.takaobrog.iftest.domain.network.NetworkStatusProvider
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun isOnlineTrue() {
        val fakeProvider = object : NetworkStatusProvider {
            override fun isOnline(): Boolean = true
        }

        val viewModel = MainViewModel(fakeProvider)

        assertTrue(viewModel.isOnline.value == true)
    }

    @Test
    fun isOnlineFalse() {
        val fakeProvider = object : NetworkStatusProvider {
            override fun isOnline(): Boolean = false
        }

        val viewModel = MainViewModel(fakeProvider)

        assertTrue(viewModel.isOnline.value == false)
    }
}