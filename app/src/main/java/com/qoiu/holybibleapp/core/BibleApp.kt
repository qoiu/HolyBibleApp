package com.qoiu.holybibleapp.core

import android.app.Application
import com.qoiu.holybibleapp.domain.BaseBookDataToDomainMapper
import com.qoiu.holybibleapp.domain.BooksInteractor
import com.qoiu.holybibleapp.data.BooksRepository

class BibleApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val booksRepository: BooksRepository = TODO("merge")
        val booksInteractor = BooksInteractor.Base(booksRepository,BaseBookDataToDomainMapper())
    }
}