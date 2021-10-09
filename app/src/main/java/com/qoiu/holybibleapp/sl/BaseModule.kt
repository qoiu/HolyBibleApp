package com.qoiu.holybibleapp.sl

import androidx.lifecycle.ViewModel

interface BaseModule<T: ViewModel> {
    fun getViewModel(): T
}