package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Book
import com.qoiu.holybibleapp.presentation.BooksUi

interface BooksDomainToUiMapper: Abstract.Mapper{

    fun map(books: List<Book>): BooksUi

    fun map(errorType: ErrorType): BooksUi
}