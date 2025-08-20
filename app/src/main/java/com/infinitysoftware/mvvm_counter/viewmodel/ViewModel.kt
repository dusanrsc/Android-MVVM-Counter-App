package com.infinitysoftware.mvvm_getdata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    private val _count = MutableLiveData(0)
    private val _countStep = MutableLiveData(1)

    val count: LiveData<Int> = _count
    val countStep: LiveData<Int> = _countStep

    fun counterIncrement() {
        val currentCountValue = _count.value ?: 0
        _count.value = currentCountValue + countStep.value
    }

    fun counterDecrement() {
        val currentCountValue = _count.value ?: 0
        _count.value = currentCountValue - countStep.value
    }

    fun counterReset() {
        _count.value = 0
    }

    fun counterStepIncrement() {
        val currentCountStepValue = _countStep.value ?: 0
        _countStep.value = currentCountStepValue + 1
    }

    fun counterStepDecrement() {
        val currentCountStepValue = _countStep.value ?: 0
        val currentCountStepBound = currentCountStepValue - 1
        _countStep.value = if (currentCountStepBound < 1) 1 else currentCountStepBound
    }

    fun counterStepReset() {
        _countStep.value = 1
    }
}