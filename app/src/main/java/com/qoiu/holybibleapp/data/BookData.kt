package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.data.net.BookServerModel
import com.qoiu.holybibleapp.data.net.BooksDataToDomainMapper
import com.qoiu.holybibleapp.domain.BookDomain
import java.lang.Exception

sealed class BookData: Abstract.Object<BookDomain, BooksDataToDomainMapper>() {

    class Success(private val books: List<BookServerModel>) : BookData() {
        override fun map(mapper: BooksDataToDomainMapper): BookDomain {
            return mapper.map(books)
        }
    }

    class Fail(private val exception: Exception) : BookData(){
        override fun map(mapper: BooksDataToDomainMapper): BookDomain {
            return mapper.map(exception)
        }
    }

}