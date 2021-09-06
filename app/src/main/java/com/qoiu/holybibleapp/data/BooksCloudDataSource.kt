package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.data.net.BookCloud
import com.qoiu.holybibleapp.data.net.BookService

interface BooksCloudDataSource{
    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val service: BookService): BooksCloudDataSource{
        override suspend fun fetchBooks(): List<BookCloud> {
            return service.fetchBooks()
        }
    }
}