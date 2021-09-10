package com.qoiu.holybibleapp.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.qoiu.holybibleapp.core.Book
import com.qoiu.holybibleapp.data.net.BookCloud
import com.qoiu.holybibleapp.data.net.BookService
import okhttp3.ResponseBody

interface BooksCloudDataSource{
    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val service: BookService): BooksCloudDataSource{
        private val gson = Gson()
        private val type = object : TypeToken<List<BookCloud>>(){}.type
        override suspend fun fetchBooks() =
            gson.fromJson<List<BookCloud>>(service.fetchBooks().string(),type)
    }
}
