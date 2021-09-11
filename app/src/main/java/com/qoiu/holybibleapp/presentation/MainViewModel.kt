package com.qoiu.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Book
import com.qoiu.holybibleapp.domain.BooksDomainToUiMapper
import com.qoiu.holybibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val communication: BooksCommunication,
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper
) : ViewModel() {//todo interface

    fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {

        val resultDomain = booksInteractor.fetchBooks()
        withContext(Dispatchers.Main) {
            val resultUi = resultDomain.map(mapper)
            resultUi.map(Abstract.Mapper.Empty())
        }
    }

    fun observeSuccess(owner: LifecycleOwner, observer: Observer<List<Book>>) =
        communication.observeSuccess(owner, observer)
}