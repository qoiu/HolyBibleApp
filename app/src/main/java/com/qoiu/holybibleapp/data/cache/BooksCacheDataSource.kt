package com.qoiu.holybibleapp.data.cache

import com.qoiu.holybibleapp.data.BookData
import com.qoiu.holybibleapp.data.BookDataToDBMapper

interface BooksCacheDataSource {

    fun fetchBooks(): List<BookDB>

    fun saveBooks(books: List<BookData>)

    class Base(private val realmProvider: RealmProvider, private val mapper: BookDataToDBMapper) :
        BooksCacheDataSource {
        override fun fetchBooks(): List<BookDB> {
            realmProvider.provide().use { realm ->
                val booksDb = realm.where(BookDB::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(booksDb)
            }
        }

        override fun saveBooks(books: List<BookData>) =
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    books.forEach { book ->
                        book.mapTo(mapper, DbWrapper.Base(realm))
                    }
                }
            }
    }
}