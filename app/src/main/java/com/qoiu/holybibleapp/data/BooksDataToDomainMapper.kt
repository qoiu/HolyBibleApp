package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.domain.BooksDomain

interface BooksDataToDomainMapper: Abstract.Mapper {

    fun map(books: List<BookData>): BooksDomain
    fun map(e: Exception): BooksDomain

}