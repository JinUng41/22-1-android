package com.example.lab5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val MyLiveData = MutableLiveData<Int>()

    init {
        MyLiveData.value=0
    }

    fun increase() {
        MyLiveData.value=(MyLiveData.value?:0)+1
    }
}