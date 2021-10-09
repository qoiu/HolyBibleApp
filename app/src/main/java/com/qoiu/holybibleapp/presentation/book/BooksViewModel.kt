package com.qoiu.holybibleapp.presentation.book

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qoiu.holybibleapp.core.Save
import com.qoiu.holybibleapp.domain.book.BooksDomainToUiMapper
import com.qoiu.holybibleapp.domain.book.BooksInteractor
import com.qoiu.holybibleapp.presentation.main.NavigationCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication,
    private val uiDataCache: UiDataCache,
    private val bookCache: Save<Pair<Int, String>>,
    private val navigator: BooksNavigator,
    private val navigationCommunication: NavigationCommunication
) : ViewModel() {
    fun fetchBooks(){
        communication.map(listOf(BookUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            resultUi.cache(uiDataCache)
            withContext(Dispatchers.Main){
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>){
        communication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) = communication.map(uiDataCache.changeState(id))

    fun save() = uiDataCache.saveState()


    fun showBook(id: Int, name: String){
        bookCache.save(Pair(id,name))
        navigator.nextScreen(navigationCommunication)
    }

    fun init(){
        navigator.saveBookScreen()
        fetchBooks()
    }

    override fun onCleared() {
        uiDataCache.saveState()
        super.onCleared()
    }
}
