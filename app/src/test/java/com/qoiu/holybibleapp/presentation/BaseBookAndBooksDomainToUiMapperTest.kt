package com.qoiu.holybibleapp.presentation

import com.qoiu.holybibleapp.R
import com.qoiu.holybibleapp.domain.BookDomainToUiMapper
import com.qoiu.holybibleapp.domain.ErrorType
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException

class BaseOldBooksDomainToUiMapperTest {
    @Test
    fun test_fail() {
        val resourceProvider = TestResourceProvider()
        val mapper = BaseBooksDomainToUiMapper(resourceProvider,object : BookDomainToUiMapper{
            override fun map(id: Int, name: String): BookUi {
                throw IllegalStateException()
            }
        })
        var actual= mapper.map(ErrorType.NO_CONNECTION)
        var expected = BooksUi.Base(listOf(BookUi.Fail("no connection")))
        assertEquals(expected, actual)
        actual= mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        expected = BooksUi.Base(listOf(BookUi.Fail("service unavailable")))
        assertEquals(expected, actual)
        actual= mapper.map(ErrorType.GENERIC_ERROR)
        expected = BooksUi.Base(listOf(BookUi.Fail("generic")))
        assertEquals(expected, actual)
    }

    @Test
    fun book_domain_to_ui_mapper_base(){
        val mapper = BaseBookDomainToUiMapper(TestResourceProvider())
        val actual = mapper.map(1,"genesis")
        val expected = BookUi.Base(1,"genesis")
        assertEquals(expected, actual)
    }

    @Test
    fun book_domain_to_ui_mapper_testament(){
        val mapper = BaseBookDomainToUiMapper(TestResourceProvider())
        var actual = mapper.map(Int.MIN_VALUE,"old")
        var expected = BookUi.Testament(Int.MIN_VALUE,"old")
        assertEquals(expected, actual)
        actual = mapper.map(Int.MAX_VALUE,"new")
        expected = BookUi.Testament(Int.MAX_VALUE,"new")
        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider() : ResourceProvider {
        override fun getString(id: Int) = when (id) {
            R.string.no_connection_message -> "no connection"
            R.string.service_unavailable_message -> "service unavailable"
            R.string.old_testament->"old"
            R.string.new_testament->"new"
            else -> "generic"
        }
    }
}