package com.qoiu.holybibleapp.presentation

import com.qoiu.holybibleapp.domain.BookDomain
import com.qoiu.holybibleapp.domain.BookDomainToUiMapper
import com.qoiu.holybibleapp.domain.BooksDomainToUiMapper
import com.qoiu.holybibleapp.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper {
    override fun map(books: List<BookDomain>) = BooksUi.Success(books,bookMapper)
    override fun map(errorType: ErrorType) = BooksUi.Fail(errorType, resourceProvider)
}