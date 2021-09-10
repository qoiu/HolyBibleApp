package com.qoiu.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.qoiu.holybibleapp.core.Book

interface BooksCommunication {

    fun show(books: List<Book>)
    fun show(errorMessage: String)

    fun observeSuccess(owner: LifecycleOwner, observer: Observer<List<Book>>)
    fun observeFail(owner: LifecycleOwner, observer: Observer<String>)

    class Base(): BooksCommunication{
        private val successLiveData = MutableLiveData<List<Book>>()
        private val failLiveData = MutableLiveData<String>()
        override fun show(books: List<Book>) {
            successLiveData.postValue(books)
        }

        override fun show(errorMessage: String) {
            failLiveData.postValue(errorMessage)
        }

        override fun observeSuccess(owner: LifecycleOwner, observer: Observer<List<Book>>) {
            successLiveData.observe(owner,observer)
        }

        override fun observeFail(owner: LifecycleOwner, observer: Observer<String>) {
            failLiveData.observe(owner,observer)
        }
    }
}
