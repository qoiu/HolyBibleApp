package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.presentation.BooksUi

interface BooksDomainToUiMapper: Abstract.Mapper{

    fun map(books: List<BookDomain>): BooksUi

    fun map(errorType: ErrorType): BooksUi
}