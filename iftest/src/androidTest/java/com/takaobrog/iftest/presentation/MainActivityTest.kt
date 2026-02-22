package com.takaobrog.iftest.presentation

import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.takaobrog.iftest.R
import com.takaobrog.iftest.fake.FakeNetworkStatusProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    // LiveDataを即時実行にする（念のため）
    @get:Rule(order = 1)
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // 本番DIを差し替える（NetworkStatusProvider をテスト用に固定）
    @Inject
    lateinit var fakeNetworkStatusProvider: FakeNetworkStatusProvider

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun isOnlineTrue() {
        fakeNetworkStatusProvider.online = true

        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val tv = activity.findViewById<TextView>(R.id.isOnline)
                assertEquals("true", tv.text.toString())
            }
        }
    }

    @Test
    fun isOnlineFalse() {
        fakeNetworkStatusProvider.online = false

        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val tv = activity.findViewById<TextView>(R.id.isOnline)
                assertEquals("false", tv.text.toString())
            }
        }
    }
}