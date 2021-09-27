package com.qoiu.holybibleapp.presentation

import com.qoiu.holybibleapp.R
import com.qoiu.holybibleapp.domain.BookDomain
import com.qoiu.holybibleapp.domain.BookDomainToUiMapper
import com.qoiu.holybibleapp.domain.BooksDomainToUiMapper
import com.qoiu.holybibleapp.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper {
    override fun map(books: List<BookDomain>): BooksUi {
        val booksUi = books.map {
            it.map(bookMapper)
        }
        return BooksUi.Base(booksUi)
    }

    override fun map(errorType: ErrorType): BooksUi {
        val messageId = when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            else -> R.string.something_went_wrong
        }
        val message = resourceProvider.getString(messageId)
        return BooksUi.Base(listOf(BookUi.Fail(message)))

    }
}