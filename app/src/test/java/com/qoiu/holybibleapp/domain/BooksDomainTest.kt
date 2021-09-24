package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.data.BookData
import com.qoiu.holybibleapp.presentation.BookUi
import com.qoiu.holybibleapp.presentation.BooksUi
import org.junit.Assert.*
import org.junit.Test

class BooksDomainTest {

    @Test
    fun test_map() {
        val domain = BooksDomain.Success(listOf(
            BookData(1, "genesis", "ot"),
            BookData(2, "revelation", "nt")
        ), object : BookDataToDomainMapper {
            override fun map(id: Int, name: String): BookDomain {
                return BookDomain.Base(id, name)
            }
        })
        val bookMapper1 = object : BookDomainToUiMapper {
            override fun map(id: Int, name: String)= BookUi.Base(id, name)
        }
        val actual = domain.map(object : BooksDomainToUiMapper {
            override fun map(books: List<BookDomain>) = BooksUi.Success(books, bookMapper1)

            override fun map(errorType: ErrorType) = throw IllegalAccessException()
        })
        val expected = BooksUi.Success(
            listOf(
                BookDomain.Testament(TestamentType.OLD),
                BookDomain.Base(1, "genesis"),
                BookDomain.Testament(TestamentType.NEW),
                BookDomain.Base(2, "revelation")
            ), bookMapper1
        )

        assertEquals(expected, actual)

    }
}