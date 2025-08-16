package com.infinitysoftware.mvvm_getdata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int> = _count

    fun counterIncrement() {
        val currentCountValue = _count.value ?: 0
        _count.value = currentCountValue + 1
    }

    fun counterDecrement() {
        val currentCountValue = _count.value ?: 0
        _count.value = currentCountValue - 1
    }

    fun counterReset() {
        _count.value = 0
    }
}