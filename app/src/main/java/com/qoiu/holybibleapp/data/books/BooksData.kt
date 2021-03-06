package com.qoiu.holybibleapp.data.books

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.domain.book.BooksDataToDomainMapper
import com.qoiu.holybibleapp.domain.book.BooksDomain
import java.lang.Exception

sealed class BooksData : Abstract.Object<BooksDomain, BooksDataToDomainMapper> {

    data class Success(private val books: List<BookData>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain = mapper.map(books)
    }

    data class Fail(private val exception: Exception) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain = mapper.map(exception)
    }
}
