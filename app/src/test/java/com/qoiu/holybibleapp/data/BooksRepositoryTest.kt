package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.data.cache.BookDB
import com.qoiu.holybibleapp.data.cache.BooksCacheDataSource
import com.qoiu.holybibleapp.data.cache.BooksCacheMapper
import com.qoiu.holybibleapp.data.net.BookCloud
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.net.UnknownHostException

class BooksRepositoryTest : BaseBooksRepositoryTest() {

    private val unknownHostException = UnknownHostException()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(false)
        val testCacheDataSource = TestBooksCacheDataSource(false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Fail(unknownHostException)

        assertEquals(expected, actual)
    }

    @Test
    fun test_with_connection_no_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(true)
        val testCacheDataSource = TestBooksCacheDataSource(false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                BookData(0, "name 0", "ot"),
                BookData(1, "name 1", "ot"),
                BookData(2, "name 2", "ot")
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(false)
        val testCacheDataSource = TestBooksCacheDataSource(true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                BookData(10, "name10", "ot"),
                BookData(20, "name20", "ot"),
                BookData(30, "name30", "ot")
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(true)
        val testCacheDataSource = TestBooksCacheDataSource(true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                BookData(10, "name10", "ot"),
                BookData(20, "name20", "ot"),
                BookData(30, "name30", "ot")
            )
        )
        assertEquals(expected, actual)
    }


    class TestBooksCacheDataSource(
        private val returnSuccess: Boolean,
        private val errorTypeNoConnection: Boolean = true
    ) : BooksCacheDataSource {

        override fun fetchBooks(): List<BookDB> {
            return if (returnSuccess) {

                listOf(
                    BookDB().apply {
                        id = 10
                        name = "name10"
                        testament = "ot"
                    },
                    BookDB().apply {
                        id = 20
                        name = "name20"
                        testament = "ot"
                    },
                    BookDB().apply {
                        id = 30
                        name = "name30"
                        testament = "ot"
                    })
            } else {
                emptyList()
            }
        }

        override fun saveBooks(books: List<BookData>) {

        }
    }

    private inner class TestBooksCloudDataSource(
        private val returnSuccess: Boolean
    ) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            if (returnSuccess) {
                return listOf(
                    BookCloud(0, "name 0", "ot"),
                    BookCloud(1, "name 1", "ot"),
                    BookCloud(2, "name 2", "ot")
                )
            } else {
                throw unknownHostException

            }
        }
    }
}