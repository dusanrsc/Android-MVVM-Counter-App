package com.infinitysoftware.mvvm_getdata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infinitysoftware.mvvm_counter.model.CounterModel

class CounterViewModel: ViewModel() {

    private var counterModel = CounterModel()
    private val _counter = MutableLiveData(counterModel)
    val counter: LiveData<CounterModel> = _counter

    fun counterIncrement() {
        val newCount = counterModel.count + counterModel.countStep
        counterModel = counterModel.copy(count = newCount)
        _counter.value = counterModel
    }

    fun counterDecrement() {
        val newCount = counterModel.count - counterModel.countStep
        counterModel = counterModel.copy(count = newCount)
        _counter.value = counterModel
    }

    fun counterReset() {
        counterModel = counterModel.copy(count = 0)
        _counter.value = counterModel
    }

    fun counterStepIncrement() {
        val newStep = (counterModel.countStep + 1).coerceAtMost(10000)
        counterModel = counterModel.copy(countStep = newStep)
        _counter.value = counterModel
    }

    fun counterStepDecrement() {
        val newStep = (counterModel.countStep - 1).coerceAtLeast(1)
        counterModel = counterModel.copy(countStep = newStep)
        _counter.value = counterModel
    }

    fun counterStepReset() {
        counterModel = counterModel.copy(countStep = 1)
        _counter.value = counterModel
    }

    fun setCountStep(value: Int) {
        val newStep = value.coerceIn(1, 10000)
        counterModel = counterModel.copy(countStep = newStep)
        _counter.value = counterModel
    }
}