package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.data.net.BookServerModel
import com.qoiu.holybibleapp.data.net.BookService

interface BooksCloudDataSource{
    suspend fun fetchBooks(): List<BookServerModel>

    class Base(private val service: BookService): BooksCloudDataSource{
        override suspend fun fetchBooks(): List<BookServerModel> {
            return service.fetchBooks()
        }
    }
}