package com.qoiu.holybibleapp.data.net

import com.qoiu.holybibleapp.core.Abstract
import com.qoiu.holybibleapp.domain.BookDomain
import retrofit2.HttpException
import java.net.UnknownHostException

interface BooksDataToDomainMapper: Abstract.Mapper {

    fun map(books: List<BookServerModel>): BookDomain
    fun map(e: Exception): BookDomain

    class Base: BooksDataToDomainMapper{
        override fun map(books: List<BookServerModel>): BookDomain =  BookDomain.Success()


        override fun map(e: Exception): BookDomain {
            val errorType = when(e){
                is UnknownHostException -> 0 //enum class ErrorType{NoConnection
                is HttpException -> 1 //ServiceUnavailable
                else -> 2 //GenericException
            }
            return BookDomain.Fail(errorType)
        }
    }
}