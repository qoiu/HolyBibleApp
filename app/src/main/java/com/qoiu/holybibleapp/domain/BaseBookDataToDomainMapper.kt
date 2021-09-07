package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.core.Book
import com.qoiu.holybibleapp.data.BooksDataToDomainMapper

class BaseBookDataToDomainMapper: BooksDataToDomainMapper {
    override fun map(books: List<Book>): BookDomain = BookDomain.Success(books)
    override fun map(e: Exception): BookDomain = BookDomain.Fail(e)
}