package com.qoiu.holybibleapp.presentation

import com.qoiu.holybibleapp.core.Book
import com.qoiu.holybibleapp.domain.BooksDomainToUiMapper
import com.qoiu.holybibleapp.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val communication: BooksCommunication,
    private val resourceProvider: ResourceProvider
) : BooksDomainToUiMapper {
    override fun map(books: List<Book>) = BooksUi.Success(communication, books)
    override fun map(errorType: ErrorType) = BooksUi.Fail(communication, errorType, resourceProvider)
}