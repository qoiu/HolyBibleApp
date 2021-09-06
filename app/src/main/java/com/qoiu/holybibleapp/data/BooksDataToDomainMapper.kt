package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Book
import com.qoiu.holybibleapp.domain.BookDomain

interface BooksDataToDomainMapper: Abstract.Mapper {

    fun map(books: List<Book>): BookDomain
    fun map(e: Exception): BookDomain

}