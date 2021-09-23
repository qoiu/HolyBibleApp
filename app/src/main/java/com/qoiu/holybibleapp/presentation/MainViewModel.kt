package com.qoiu.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qoiu.holybibleapp.domain.BooksDomainToUiMapper
import com.qoiu.holybibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val communication: BooksCommunication,
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper
) : ViewModel() {
    //todo interface
    fun fetchBooks() {
        communication.map(listOf(BookUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observeSuccess(owner: LifecycleOwner, observer: Observer<List<BookUi>>) =
        communication.observe(owner, observer)
}