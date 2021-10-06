package com.qoiu.holybibleapp.domain.book

import com.qoiu.holybibleapp.data.books.BookData
import com.qoiu.holybibleapp.data.books.TestamentTemp
import com.qoiu.holybibleapp.domain.TestamentType

class BaseBooksDataToDomainMapper(private val bookMapper: BookDataToDomainMapper) :
    BooksDataToDomainMapper() {

    override fun map(data: List<BookData>): BooksDomain {
        val domainList = mutableListOf<BookDomain>()
        val temp = TestamentTemp.Base()
        data.forEach { bookData ->
            if (!bookData.matches(temp)) {
                val testamentType = if (temp.isEmpty())
                    TestamentType.OLD
                else
                        TestamentType.NEW
                domainList.add(BookDomain.Testament(testamentType))
                bookData.save(temp)
            }
            domainList.add(bookData.map(bookMapper))
        }
        return BooksDomain.Success(domainList)
    }

    override fun map(e: Exception) = BooksDomain.Fail(errorType(e))
}