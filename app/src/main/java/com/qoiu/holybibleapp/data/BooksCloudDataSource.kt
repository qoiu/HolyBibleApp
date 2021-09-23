package com.qoiu.holybibleapp.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.qoiu.holybibleapp.data.net.BookCloud
import com.qoiu.holybibleapp.data.net.BookService

interface BooksCloudDataSource {
    suspend fun fetchBooks(): List<BookCloud>

    class Base(
        private val service: BookService,
        private val gson: Gson
    ) : BooksCloudDataSource {
        private val type = object : TypeToken<List<BookCloud>>() {}.type // todo make a wrapper
        override suspend fun fetchBooks() =
            gson.fromJson<List<BookCloud>>(service.fetchBooks().string(), type)
    }
}
