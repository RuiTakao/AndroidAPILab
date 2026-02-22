package com.takaobrog.iftest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.takaobrog.iftest.domain.network.NetworkStatusProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkStatusProvider: NetworkStatusProvider
) : ViewModel() {
    private val _isOnline = MutableLiveData<Boolean>()
    val isOnline : LiveData<Boolean> = _isOnline

    init {
        refresh()
    }

    fun refresh() {
        _isOnline.value = networkStatusProvider.isOnline()
    }
}