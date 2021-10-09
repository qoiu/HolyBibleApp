package com.qoiu.holybibleapp.data.books.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.qoiu.holybibleapp.R
import com.qoiu.holybibleapp.core.RawResourceProvider

interface BooksCloudDataSource {
    suspend fun fetchBooks(): List<BookCloud>

    abstract class Abstract(private val gson: Gson) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> = gson.fromJson(
            getDataString(),
            object : TypeToken<List<BookCloud>>() {}.type
        )

        protected abstract suspend fun getDataString(): String
    }

    class Base(
        private val service: BookService,
        gson: Gson
    ) : Abstract(gson) {
        override suspend fun getDataString() = service.fetchBooks().string()
    }

    class Mock(
        private val rawResourceProvider: RawResourceProvider,
        gson: Gson
    ) : BooksCloudDataSource.Abstract(gson) {
        override suspend fun getDataString() =
            rawResourceProvider.readText(R.raw.test_books_response)
    }
}
