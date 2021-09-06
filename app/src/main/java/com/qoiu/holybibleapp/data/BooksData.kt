package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.core.Book
import com.qoiu.holybibleapp.domain.BookDomain
import java.lang.Exception

sealed class BooksData: Abstract.Object<BookDomain, BooksDataToDomainMapper>() {

    class Success(private val books: List<Book>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BookDomain {
            return mapper.map(books)
        }
    }

    class Fail(private val exception: Exception) : BooksData(){
        override fun map(mapper: BooksDataToDomainMapper): BookDomain {
            return mapper.map(exception)
        }
    }

}