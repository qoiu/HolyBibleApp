package com.qoiu.holybibleapp.core

import android.app.Application
import com.qoiu.holybibleapp.domain.BooksInteractor
import com.qoiu.holybibleapp.presentation.BaseBooksDomainToUiMapper
import com.qoiu.holybibleapp.presentation.BooksCommunication
import com.qoiu.holybibleapp.presentation.MainViewModel
import com.qoiu.holybibleapp.presentation.ResourceProvider

class BibleApp : Application() {

    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()

        val booksInteractor: BooksInteractor = TODO()
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(BooksCommunication.Base(), ResourceProvider.Base(this))
        )
    }
}