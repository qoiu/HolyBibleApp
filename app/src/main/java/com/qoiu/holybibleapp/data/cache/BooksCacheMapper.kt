package com.qoiu.holybibleapp.data.cache

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Book

interface BooksCacheMapper: Abstract.Mapper {

    fun map(books: List<BookDB>): List<Book>

    class Base(private val mapper: BookCacheMapper): BooksCacheMapper{
        override fun map(books: List<BookDB>) = books.map { bookDB ->
            bookDB.map(mapper)
        }
    }
}