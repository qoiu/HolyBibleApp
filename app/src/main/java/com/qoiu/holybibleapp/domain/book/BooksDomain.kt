package com.qoiu.holybibleapp.domain.book

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.ErrorType
import com.qoiu.holybibleapp.presentation.book.BooksUi

/*
*todo rename by lead
 */

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper> {

    data class Success(private val books: List<BookDomain>) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi = mapper.map(books)
    }

    data class Fail(private val e: ErrorType) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(e)
    }
}