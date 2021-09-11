package com.qoiu.holybibleapp.data

import com.qoiu.holybibleapp.core.Book
import com.qoiu.holybibleapp.data.cache.BookCacheMapper
import com.qoiu.holybibleapp.data.cache.BookDB
import com.qoiu.holybibleapp.data.cache.BooksCacheDataSource
import com.qoiu.holybibleapp.data.cache.BooksCacheMapper
import com.qoiu.holybibleapp.data.net.BookCloud
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.UnknownHostException

class BooksRepositorySaveBookTest {
    @Test
    fun test_save_books()= runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource()
        val testCacheDataSource = TestBooksCacheDataSource()
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(BookCloudMapper.Base()),
            BooksCacheMapper.Base(BookCacheMapper.Base())
        )

        val actualCloud = repository.fetchBooks()

        val expectedCloud = BooksData.Success(listOf(
            Book(0, "name 0"),
            Book(1, "name 1"),
            Book(2, "name 2")
        ))

        Assert.assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(listOf(
            Book(0, "name 0 db"),
            Book(1, "name 1 db"),
            Book(2, "name 2 db")
        ))
        Assert.assertEquals(expectedCache, actualCache)
    }



    class TestBooksCacheDataSource() : BooksCacheDataSource {
        private val list = ArrayList<BookDB>()
        override fun fetchBooks(): List<BookDB> {
            return list
        }

        override fun saveBooks(books: List<Book>) {
            books.map {book ->
                list.add(BookDB().apply{
                    this.id = book.id
                    this.name = "${book.name} db"
                })
            }
        }
    }

    private inner class TestBookCacheMapper: BookCacheMapper {
        override fun map(bookdb: BookDB) = Book(bookdb.id,bookdb.name)
    }

    private inner class TestBookCloudMapper : BookCloudMapper{
        override fun map(id: Int, name: String) = Book(id,name)
    }

    private inner class TestBooksCloudDataSource() : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
                return listOf(
                    BookCloud(0, "name 0"),
                    BookCloud(1, "name 1"),
                    BookCloud(2, "name 2")
                )
            }
        }
    }