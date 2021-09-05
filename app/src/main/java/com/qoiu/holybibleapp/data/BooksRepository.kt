package com.qoiu.holybibleapp.data

import java.lang.Exception

interface BooksRepository {

    suspend fun fetchBooks(): BookData

    class Base(private val cloudDataSource: BooksCloudDataSource) : BooksRepository {
        override suspend fun fetchBooks() = try {
            BookData.Success(cloudDataSource.fetchBooks())
        } catch (e: Exception) {
            BookData.Fail(e)
        }
    }
}