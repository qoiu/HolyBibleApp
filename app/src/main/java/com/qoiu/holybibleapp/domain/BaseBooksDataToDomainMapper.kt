package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.data.BookData
import com.qoiu.holybibleapp.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper(private val bookMapper: BookDataToDomainMapper): BooksDataToDomainMapper {
    override fun map(books: List<BookData>): BooksDomain = BooksDomain.Success(books,bookMapper)
    override fun map(e: Exception): BooksDomain = BooksDomain.Fail(e)
}