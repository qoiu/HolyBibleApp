package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Book
import com.qoiu.holybibleapp.presentation.BooksUi
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

/*
*todo rename by lead
 */

sealed class BookDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper>() {
    //todo fix

    class Success(private val books: List<Book>) : BookDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(books)
    }

    class Fail(private val e: Exception) : BookDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}