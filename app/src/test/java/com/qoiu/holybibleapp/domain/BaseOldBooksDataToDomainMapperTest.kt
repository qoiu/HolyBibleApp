package com.qoiu.holybibleapp.domain

import com.qoiu.holybibleapp.core.ErrorType
import com.qoiu.holybibleapp.data.books.BookData
import com.qoiu.holybibleapp.domain.book.BaseBooksDataToDomainMapper
import com.qoiu.holybibleapp.domain.book.BookDataToDomainMapper
import com.qoiu.holybibleapp.domain.book.BookDomain
import com.qoiu.holybibleapp.domain.book.BooksDomain
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.IllegalStateException
import java.net.UnknownHostException

class BaseOldBooksDataToDomainMapperTest {


    private val mapper = BaseBooksDataToDomainMapper(object : BookDataToDomainMapper {
        override fun map(id: Int, name: String): BookDomain {
            return BookDomain.Base(id, name)
        }
    })
    @Test
    fun test_success() {

        val actual = mapper.map(
            listOf(
                BookData(1, "genesis", "ot"),
                BookData(2, "revelation", "nt")
            )
        )

        val data = mutableListOf(
            BookDomain.Testament(TestamentType.OLD),
            BookDomain.Base(1, "genesis"),
            BookDomain.Testament(TestamentType.NEW),
            BookDomain.Base(2, "revelation")
        )

        val expected = BooksDomain.Success(data)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail(){
        var actual = mapper.map(UnknownHostException())
        var expected = BooksDomain.Fail(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)
        actual = mapper.map(IllegalStateException())
        expected = BooksDomain.Fail(ErrorType.GENERIC_ERROR)
        assertEquals(expected, actual)

    }
}