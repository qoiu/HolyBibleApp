package com.qoiu.holybibleapp.data.books.cache

import com.qoiu.holybibleapp.core.DbWrapper
import com.qoiu.holybibleapp.core.Read
import com.qoiu.holybibleapp.core.RealmProvider
import com.qoiu.holybibleapp.core.Save
import com.qoiu.holybibleapp.data.books.BookData
import com.qoiu.holybibleapp.data.books.BookDataToDBMapper
import io.realm.Realm

interface BooksCacheDataSource : Save<List<BookData>>, Read<List<BookDB>> {

    class Base(private val realmProvider: RealmProvider, private val mapper: BookDataToDBMapper) :
        BooksCacheDataSource {

        override fun save(data: List<BookData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                data.forEach { book ->
                    book.mapBy(mapper, BookDbWrapper(it))
                }
            }
        }

        override fun read(): List<BookDB> {
            realmProvider.provide().use { realm ->
                val booksDb = realm.where(BookDB::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(booksDb)
            }
        }

        private inner class BookDbWrapper(realm: Realm) : DbWrapper.Base<BookDB>(realm) {
            override fun dbClass() = BookDB::class.java
        }
    }
}
