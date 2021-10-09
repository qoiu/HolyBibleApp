package com.qoiu.holybibleapp.presentation.book

import com.qoiu.holybibleapp.core.ErrorType
import com.qoiu.holybibleapp.core.ResourceProvider
import com.qoiu.holybibleapp.domain.book.BookDomain
import com.qoiu.holybibleapp.domain.book.BookDomainToUiMapper
import com.qoiu.holybibleapp.domain.book.BooksDomainToUiMapper

class BaseBooksDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper,
    private val uiDataCache: UiDataCache
) : BooksDomainToUiMapper(resourceProvider) {

    override fun map(data: List<BookDomain>): BooksUi = uiDataCache.cache(data.map {
        it.map(bookMapper)
    })

    override fun map(errorType: ErrorType): BooksUi =
         BooksUi.Base(listOf(BookUi.Fail(errorMessage(errorType))))

}