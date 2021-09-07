package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.data.BooksDataToDomainMapper
import com.qoiu.holybibleapp.data.BooksRepository

interface BooksInteractor {

    suspend fun fetchBooks(): BookDomain

    class Base(private val booksRepository: BooksRepository,
               private val mapper: BooksDataToDomainMapper): BooksInteractor{
        override suspend fun fetchBooks()  =  booksRepository.fetchBooks().map(mapper)
    }
}