package com.qoiu.holybibleapp.presentation

import com.qoiu.holybibleapp.R
import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.domain.BookDomain
import com.qoiu.holybibleapp.domain.BookDomainToUiMapper
import com.qoiu.holybibleapp.domain.ErrorType

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {
    class Success(
        private val books: List<BookDomain>,
        private val bookMapper: BookDomainToUiMapper
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val booksUi = books.map {
                it.map(bookMapper)
            }
            mapper.map(booksUi)
        }
    }

    class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val messageId: Int = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }

            val message = resourceProvider.getString(messageId)
            mapper.map(listOf(BookUi.Fail(message)))
        }
    }
}
