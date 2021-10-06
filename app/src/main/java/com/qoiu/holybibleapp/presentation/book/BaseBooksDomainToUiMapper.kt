package com.qoiu.holybibleapp.presentation.book

import com.qoiu.holybibleapp.R
import com.qoiu.holybibleapp.core.ErrorType
import com.qoiu.holybibleapp.core.ResourceProvider
import com.qoiu.holybibleapp.domain.book.BookDomain
import com.qoiu.holybibleapp.domain.book.BookDomainToUiMapper
import com.qoiu.holybibleapp.domain.book.BooksDomainToUiMapper

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper(resourceProvider) {

    override fun map(data: List<BookDomain>): BooksUi = BooksUi.Base(data.map {
        it.map(bookMapper)
    })

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