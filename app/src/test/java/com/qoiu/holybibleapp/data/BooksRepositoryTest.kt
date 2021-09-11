package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Book
import com.qoiu.holybibleapp.data.cache.BookCacheMapper
import com.qoiu.holybibleapp.data.cache.BookDB
import com.qoiu.holybibleapp.data.cache.BooksCacheDataSource
import com.qoiu.holybibleapp.data.cache.BooksCacheMapper
import com.qoiu.holybibleapp.data.net.BookCloud
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.net.UnknownHostException

class BooksRepositoryTest {

    private val unknownHostException = UnknownHostException()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(false)
        val testCacheDataSource = TestBooksCacheDataSource(false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(BookCloudMapper.Base()),
            BooksCacheMapper.Base(BookCacheMapper.Base())
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
            BooksCloudMapper.Base(BookCloudMapper.Base()),
            BooksCacheMapper.Base(BookCacheMapper.Base())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                Book(0, "name 0"),
                Book(1, "name 1"),
                Book(2, "name 2")
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
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                Book(10, "name10"),
                Book(20, "name20"),
                Book(30, "name30")
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
            BooksCloudMapper.Base(BookCloudMapper.Base()),
            BooksCacheMapper.Base(BookCacheMapper.Base())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(
                Book(10, "name10"),
                Book(20, "name20"),
                Book(30, "name30")
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
                    },
                    BookDB().apply {
                        id = 20
                        name = "name20"
                    },
                    BookDB().apply {
                        id = 30
                        name = "name30"
                    })
            } else {
                emptyList()
            }
        }

        override fun saveBooks(books: List<Book>) {

        }
    }

    private inner class TestBooksCloudDataSource(
        private val returnSuccess: Boolean
    ) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            if (returnSuccess) {
                return listOf(
                    BookCloud(0, "name 0"),
                    BookCloud(1, "name 1"),
                    BookCloud(2, "name 2")
                )
            } else {
                    throw unknownHostException

            }
        }
    }

    private inner class TestBookCacheMapper: BookCacheMapper{
        override fun map(bookdb: BookDB) = Book(bookdb.id,bookdb.name)
    }

    private inner class TestBookCloudMapper : BookCloudMapper{
        override fun map(id: Int, name: String) = Book(id,name)
    }
}